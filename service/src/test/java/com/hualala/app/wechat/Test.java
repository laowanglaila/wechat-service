package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.WechatErrorCode;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.grpc.CardCodeRpcData;
import com.hualala.app.wechat.impl.EventHandler.impl.CardUserGetHandler;
import com.hualala.app.wechat.sdk.mp.util.OkHttpUtil;
import com.hualala.app.wechat.sdk.mp.util.WechatBeanFactory;
import org.apache.commons.codec.binary.Base64;
import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.ProxyGenerator;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


/**
 * Created by renjianfei on 2017/7/12.
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    /**
     * 捕捉RuntimeException，成功
     */
    @org.junit.Test
    public void test() {
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(l / 1000);
        String s = "123x";
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println(s);
        }

    }

    /**
     * Base64编解码
     */
    @org.junit.Test
    public void test1() {
        String groupID = "123";
        String hualalaCardID = "hualalaCardID";
        String hualalaCardCode = "hualalaCardCode";
        String json = "{\"groupID\":\"" + groupID + "\",\"hualalaCardID\":\"" + hualalaCardID + "\",\"hualalaCardCode\":\"" + hualalaCardCode + "\"}";
        System.out.println("json:" + json);
        String s = Base64.encodeBase64URLSafeString(json.getBytes());
        System.out.println("encode-json:" + s);
        byte[] bytes = Base64.decodeBase64(s);
        String s1 = new String(bytes);
        System.out.println("decode-json:" + s1);
        JSONObject jsonObject = JSONObject.parseObject(s1);
        System.out.println(jsonObject);
    }

    /**
     * BigDecimal精度测试
     */
    @org.junit.Test
    public void test2() {
        String balance = "1000.99";
        BigDecimal bigDecimal = new BigDecimal(balance);
        BigDecimal bigDecimal1 = new BigDecimal("100");
        BigDecimal decimal = bigDecimal.multiply(bigDecimal1);
        System.out.println(decimal);
        System.out.println(decimal.longValue());
    }

    /**
     * replaceAll测试
     */
    @org.junit.Test
    public void test3() {
        String numString = "0-1-1-0";
        String s = numString.replaceAll("-", "");
        try {
            Long i = Long.parseLong(s);
        } catch (Throwable i) {

        }

    }

    /**
     * BigDecimal比较精度测试，不丢失精度的情况下，相等
     */
    @org.junit.Test
    public void test4() {
        BigDecimal decimal1 = new BigDecimal(1.0000);
        BigDecimal decimal2 = new BigDecimal(1.000);
        int i = decimal1.compareTo(decimal2);
        System.out.println(i);

    }

    /**
     * 生成微信错误码枚举值
     */
    @org.junit.Test
    public void test5() {

        Set<String> keySet = WechatErrorCode.wechatError.keySet();
        int errorcode = 112300;
        for (String key : keySet) {
            String value = WechatErrorCode.wechatError.get(key);
            System.out.println("WECHAT_ERROR_" + key + "(\"00" + errorcode++ + "\",\"" + value + "\"," + key + "),\r\n");
        }

    }

    /**
     * 输出枚举值name
     */
    @org.junit.Test
    public void test6() {
        System.out.println(WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS);
    }

    /**
     * 读取ErrorCodes.java中的常量，全部转为枚举值输出
     *
     * @throws IOException
     */
    @org.junit.Test
    public void test7() throws IOException {
        int lineNumber = 0;
        File file = null;
        InputStreamReader read = null;
        LineNumberReader lineNumberReader = null;
        String lineTxt = null;
        String encoding = "UTF-8";
        file = new File("E:\\GitRepository\\wechat-service\\service\\src\\main\\java\\com\\hualala\\app\\wechat\\common\\ErrorCodes.java");
        read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String content = "";
        String message = "";
        content = bufferedReader.readLine();
        while (content != null) {
            if (content.contains("//")) {
                message = content.trim();
//                System.out.println(content);
            } else if ("".equals(content.trim()) || content.contains(".") || content.contains("}")) {
                message = "";
            } else {
                String[] split = content.trim().split(" ");
                System.out.println(split[1] + "(" + split[3].replace(";", "") + ",\"" + message.replace("//", "").trim() + "\"),");
                message = "";
            }
            content = bufferedReader.readLine();
        }
    }

    /**
     * 测试基本类型是否数据包装类型，结果：true
     */
    @org.junit.Test
    public void test8() {
        int i = 1;
        Object i1 = i;
        System.out.println(i1 instanceof Integer);
    }

    /**
     * 测试null使用valueof转换为String后的值，结果：“null”
     */
    @org.junit.Test
    public void test9() {
        Object s = null;
        String s1 = String.valueOf(s);
        System.out.println(s1);
    }

    @org.junit.Test
    public void test10() {
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        String uuid = fromStringWhitoutHyphens(s.replace("-", ""));
        System.out.println(uuid);
        System.out.println(Long.parseLong(uuid));
    }

    public static String fromStringWhitoutHyphens(String str) {
        if (str.length() != 32) {
            throw new IllegalArgumentException("Invalid UUID string: " + str);
        }
        String s1 = str.substring(0, 8);
        long l = Long.parseLong(s1, 16);
        String s2 = str.substring(8, 12);
        long l1 = Long.parseLong(s2, 16);
        String s3 = str.substring(12, 16);
        long l2 = Long.parseLong(s3, 16);
        String s4 = str.substring(16, 20);
        long l3 = Long.parseLong(s4, 16);
        String s5 = str.substring(20, 32);
        long l4 = Long.parseLong(s5, 16);
        System.out.println("---------" + s1 + s2 + s3 + s4 + s5);
        byte[] bytes = str.getBytes(Charset.forName("ISO-8859-1"));
        java.lang.String s = new java.lang.String(bytes);

        long mostSigBits = Long.decode("0x" + s1).longValue();
        mostSigBits <<= 16;
        mostSigBits |= Long.decode("0x" + s2).longValue();
        mostSigBits <<= 16;
        mostSigBits |= Long.decode("0x" + s3).longValue();
        long leastSigBits = Long.decode("0x" + s4).longValue();
        leastSigBits <<= 48;
        leastSigBits |= Long.decode("0x" + s5).longValue();
        System.out.println("mostSigBits:"+mostSigBits);
        System.out.println("leastSigBits:"+leastSigBits);
        return ""+l + l1 + l2 + l3 + l4;


    }

    /**
     * 数组转list
     */
    @org.junit.Test
    public void test11(){
        String openIds = "1,2,3,4,5";
        String content = "eee";
        String[] openIDs = openIds.split(",");
        String json = JSONArray.toJSONString(openIDs);
        String params = "{" +
                "\"touser\":" + json + "," +
                "\"msgtype\": \"text\"," +
                "\"text\": { \"content\": \"" + content + "\"}" +
                "}";
        System.out.println(params);
    }

    @org.junit.Test
    public void test12(){
        String url = "http://dohko.api.promotion.service.hualala.com/feature/addCardFeature";
        String params = "{\n" +
                        "\"cardID\": 557646,\n" +
                        "\"eventID\": 6439582021810398339,\n" +
                        "\"groupID\": 1155\n" +
                        "}";
        JSONObject post = OkHttpUtil.post( url, params );
        System.out.println(post);
    }
    @org.junit.Test
    public void test13(){
        String url = "http://dohko.api.promotion.service.hualala.com/feature/queryFeature";
        String params = "{\n" +
//                        "\"cardID\": 0,\n" +
                        "\"eventID\": \"\",\n" +
                        "\"groupID\": 1155,\n" +
//                        "\"cardTypeID\": 0,\n" +
                        "\"date\": 0\n" +
                        "}";
        JSONObject post = OkHttpUtil.post( url, params );
        System.out.println(post);
    }
    @org.junit.Test
    public void test14(){
        Class <?> builderClass = CardCodeRpcData.CardCodeDestroyReqData.Builder.class;
        Class <?> superclass = builderClass.getSuperclass();
        String canonicalName = builderClass.getCanonicalName();
        Class <?> declaringClass = builderClass.getDeclaringClass();
        System.out.println();
    }
    @org.junit.Test
    public void test15(){
        ThreadLocal <String> stringThreadLocal = new ThreadLocal <>();
        stringThreadLocal.set( "123" );
        String s = stringThreadLocal.get();
    }
    @org.junit.Test
    public void test16(){
        CardUserGetHandler bean = WechatBeanFactory.getBean( CardUserGetHandler.class );
        System.out.println(bean);
        bean.handler( new JSONObject( ) );
    }
    @org.junit.Test
    public void test17(){
        Map<String, Object> map = new HashMap <>();
        String shopID = map.get( "shopID" )==null?"":(String) map.get( "shopID" );
        System.out.println("123"+shopID + "123");
    }

}

/**
 * 被代理父类
 */
class SayHello {
    public void say() {
        System.out.println("hello everyone");
    }
}

/**
 * cglig代理
 */
class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    //实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("后置代理");
        return result;
    }
}

/**
 * 测试方法
 */
class DoCGLib {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        SayHello proxyImp = (SayHello) proxy.getProxy(SayHello.class);
        proxyImp.say();
    }
}
//----------------------------------------------------------------------------------------------------------------
interface Subject
{
    public void doSomething();
}
class RealSubject implements Subject
{
    public void doSomething()
    {
        System.out.println( "call doSomething()" );
    }
}
class ProxyHandler implements InvocationHandler
{
    private Object proxied;

    public ProxyHandler( Object proxied )
    {
        this.proxied = proxied;
    }

    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
    {
        //在转调具体目标对象之前，可以执行一些功能处理

        //转调具体目标对象的方法
        System.out.println("JDK动态代理前置操作");
        Object invoke = method.invoke(proxied, args);

        //在转调具体目标对象之后，可以执行一些功能处理
        System.out.println("JDK动态代理后置操作");
        return invoke;
    }
}

class DynamicProxy {
    public static void main( String args[] )
    {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();

        //write proxySubject class binary data to file
        createProxyClassFile();
    }

    static void createProxyClassFile()
    {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
        try
        {
            FileOutputStream out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}