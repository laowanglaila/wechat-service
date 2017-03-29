package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.mapper.WechatMemberInfoMapper;
import com.hualala.app.wechat.model.WechatMemberInfoModel;
import com.hualala.app.wechat.util.HttpApiUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by renjianfei on 2017/3/23.
 *
 * 根据本地的txt文件中cardID和Cord，从微信公众服务平台获取会员信息json，并保存到本地数据库。
 *
 */
@Service
public class FastMemberInfoService {

    private Logger logger = LoggerFactory.getLogger(ComponentTokenService.class);

    private String accessToken = null;
    private Long iflush = 0L;


    private String appId = "wx58e9eacc01f7880c";
    private String appSecret = "bb43ed30da3409fb1a8ef2b432019332";

    private int startLine = 0;
    private int totalLine = 0;
    private int cacheNo = 500;
    private int threadNO = 50;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setThreadNO(int threadNO) {
        this.threadNO = threadNO;
    }

    public void setCacheNo(int cacheNo) {
        this.cacheNo = cacheNo;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


    @Autowired
    private WechatMemberInfoMapper memberInfoMapper;

    @Autowired
    private ApplicationContext ctx;

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    /**
     * 获取本地的txt文件中cardID和Cord
     */
    public void loadInfo(String filePath,int startLine){
        setStartLine(startLine);
        loadInfo(filePath);
    }
    public void loadInfo(String filePath){

        int lineNumber = 0;
        File file = null;
        InputStreamReader read = null;

        try {
            String encoding="GBK";
            file=new File(filePath);
            if(!file.isFile() || !file.exists()){ //判断文件是否存在
                logger.debug("找不到指定的文件");
                return;
            }
            read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bReader = new BufferedReader(read);
            LineNumberReader lineReader = new LineNumberReader(bReader);
            String lineTxt = null;
            while((lineTxt = lineReader.readLine()) != null ){
                totalLine++;
            }
            lineReader.close();

            while (startLine < totalLine ){
                System.out.println("主线程main开始工作：--------从第 [ "+startLine+" ] 调数据开始------");
                ArrayList<String> textCache = new ArrayList<String>();
                read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);
                while(true){
                    lineTxt = lineNumberReader.readLine();
                    lineNumber = lineNumberReader.getLineNumber();
                    if(lineTxt == null){
                        if((totalLine - startLine) < cacheNo*2){
                            startLine += cacheNo;
                        }
                        break;
                    }
                    //请求开始行以后的数据
                    if(lineNumber >= startLine && lineNumber < startLine + cacheNo) {
                        textCache.add(lineTxt);
                        logger.info("正在读取: [ "+file.getAbsolutePath()+" ]的第[ "+lineNumber+" ]行数据！");
                    }else if(lineNumber == startLine + cacheNo){
                        startLine = lineNumber;
                        break;
                    }
                }
                read.close();
                //主线程等待控制
                CountDownLatch count = new CountDownLatch(threadNO);
//                 // TODO: 2017/3/29    使用callPoolRequestMethod增加事务控制
//                Future<String> future = callPoolRequestMethod(textCache);

                //多线程请求微信会员接口
                for (int i = 0 ; i< threadNO ; i++){
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                String lineText = null;
                                synchronized (textCache) {
                                    if (textCache.isEmpty()) {
                                        System.out.println("--------------"+Thread.currentThread().getName()+"子线程跳出微信请求循环------------------");
                                        count.countDown();
                                        break;
                                    }
                                    lineText = textCache.remove(0 );

                                    logger.info("操作第 [ "+(startLine-cacheNo)+"--"+(startLine-1)+" ] 的第 [ "+(cacheNo-count.getCount())+" ] 条数据");
                                }
                                try {
                                    textRequestMethod(lineText);
                                } catch (Exception e) {
                                    logger.error("在操作: [ " + lineText + " ]时出错！");
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, "Thread : " + i);
                    thread.start();
                }
                count.await();

            }
        } catch (Exception e) {
            logger.error("在操作: [ "+file.getAbsolutePath()+" ]的第[ "+(startLine-cacheNo)+"--"+(startLine-1)+" ]行出错！");
            e.printStackTrace();
        }finally {
            try {
                if(null != read)
                    read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 多线程创建请求微信接口方法
     */
    public Future<String> callPoolRequestMethod(List<String> textCache) throws Exception {

        //手动开启事务
        DataSourceTransactionManager txManager = (DataSourceTransactionManager) ctx.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
        TransactionStatus txStatus = txManager.getTransaction(def);// 获得事务状态

        txManager.commit(txStatus);//事务处理成功，提交当前事务

        txManager.rollback(txStatus);//事务处理失败，回滚当前事务  

        CountDownLatch count = new CountDownLatch(threadNO);
        ExecutorService exec = Executors.newFixedThreadPool(threadNO);
        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
                while (true) {
                    String lineText = null;
                    synchronized (textCache) {
                        if (textCache.isEmpty()) {
                            System.out.println("--------------"+Thread.currentThread().getName()+"子线程跳出微信请求循环------------------");
                            count.countDown();
                            break;
                        }
                        lineText = textCache.remove(0 );

                        logger.info("操作第 [ "+(startLine-cacheNo)+"--"+(startLine-1)+" ] 的第 [ "+(cacheNo-count.getCount())+" ] 条数据");
                    }
                        textRequestMethod(lineText);
                }
                return "success";
            }
        };
        Future<String> task = exec.submit(call);
        count.await();
        //关闭线程池
        exec.shutdown();
        return task;
    }

    /**
     * 根据text数据请求微信会员数据并存入数据库
     */
    public void textRequestMethod(String lineTxt) throws Exception {
        Map<String, String> params = new HashMap<>();
        String[] split = lineTxt.split(" ");
        params.put("card_id", split[0]);
        params.put("code", split[1]);
        System.out.println(params);
        // 访问微信接口
        JSONObject jsonObject = visitWeChat(params);

        //创建数据库参数
        WechatMemberInfoModel memberInfoModel = new WechatMemberInfoModel();
        memberInfoModel.setWechatJson(jsonObject.toJSONString());
        memberInfoModel.setErrcode(Integer.parseInt(jsonObject.getString("errcode")));
        memberInfoModel.setAppID(appId);
        memberInfoModel.setCardID(split[0]);
        memberInfoModel.setCode(split[1]);
        //持久化到数据库

        synchronized (memberInfoMapper){
            memberInfoMapper.insert(memberInfoModel);
        }


    }


    /**
     *获取一条会员json，如果accessToken无效重新获取
     * @param params
     * @return
     */
    private JSONObject visitWeChat(Map<String,String> params) throws Exception {

        if(StringUtils.isBlank(accessToken)){
            getAccessToken();
        }

        String jsonParams = JSONObject.toJSONString(params);
        System.out.println("请求json ：------ "+jsonParams);
        String url = "https://api.weixin.qq.com/card/membercard/userinfo/get?access_token="+accessToken;
        JSONObject jsonObject = null;

            jsonObject = HttpApiUtil.httpPost(url, jsonParams);

            if(null == jsonObject){
                throw new RuntimeException("httpPost请求memberInfo异常！！！");
            }
        System.out.println("响应json ：------ "+jsonObject.toJSONString());

        String errcode = jsonObject.getString("errcode");
        if(!"0".equals(errcode)){
            //accessToken超时
            if("42001".equals(errcode)){
                getAccessToken();
                jsonObject = visitWeChat(params);
            }else if("-1".equals(errcode)){
                Thread.sleep(1);
                jsonObject = visitWeChat(params);
            }
        }
        return jsonObject;
    }

    /**
     * 请求会员数据之前，判断accessToken是否失效
     * 如果失效重新获取
     */
    public synchronized String getAccessToken() {
        //获取一个新的accessToken
//     appid:   wx58e9eacc01f7880c
//     appSecret:   bb43ed30da3409fb1a8ef2b432019332
        if(iflush > System.currentTimeMillis()){
            return accessToken;
        }
        String url = WechatBaseApi.GET_ACCESS_TOKEN + "&appid=" + appId + "&secret=" + appSecret;
        JSONObject resultJson = HttpApiUtil.httpGet(url);
        accessToken = resultJson.getString("access_token");
        Long expiresIn = Long.parseLong(resultJson.getString("expires_in"));
        System.out.println("------------accessToken有效时间-------------: "+expiresIn+"s ");
        long currentTime = System.currentTimeMillis();
        iflush = currentTime + (expiresIn-200)*1000;
        logger.debug("获取到的AccessToken:["+accessToken+"]");
        return accessToken;
    }





}
