package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatTemplateRpcService;
import com.hualala.app.wechat.WechatTemplateTypeEnum;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.config.RabbitQueueProps;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.model.WechatTemplateModel;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.WechatTemplateService;
import com.hualala.app.wechat.service.user.WechatUserService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.template.WechatTemplate;
import com.hualala.app.wechat.util.template.WechatTemplateConstants;
import com.hualala.app.wechat.util.template.WechatTemplateFatory;
import com.hualala.core.app.Logger;
import com.hualala.core.client.BaseRpcClient;
import com.hualala.core.utils.DataUtils;
import com.hualala.message.SemSMSQueueService;
import com.hualala.message.WechatMsgQueueService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 微信模板消息实现类
 * Created by xkia on 2017/4/13.
 */
@Service
public class WechatTemplateRpcServiceImpl implements WechatTemplateRpcService {

    protected Logger logger = Logger.of(WechatTemplateRpcServiceImpl.class);


    @Autowired
    private WechatTemplateService wechatTemplateService;

    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private WechatTemplateFatory wechatTemplateFatory;

    @Autowired
    private MpInfoService mpInfoService;

    @Autowired
    private BaseRpcClient rpcClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitQueueProps rabbitQueueProps;
    @Override
    public WechatTemplateRpcResData sentWechatTemplate(WechatTemplateRpcReqData reqData) {

        String mpID = reqData.getMpID();

        if(StringUtils.isEmpty(mpID)){
            mpID = mpInfoService.queryMpIDAuth(reqData.getGroupID(),reqData.getBrandID(),reqData.getShopID());
        }
        if(StringUtils.isEmpty(mpID)) {
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "未找到对应公众号");
        }

        WechatTemplateTypeEnum templateTypeEnum = reqData.getTemplateType();
        if(templateTypeEnum.getValue() == 0){
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_TEMPLATE_ERROR, "未指定模板消息类型");
        }
        String modelType =templateTypeEnum.getModelType();
        String modelSubType = templateTypeEnum.getModelSubType();

        long userID = reqData.getUserID();

        String modelID = gerModelID(modelType, modelSubType);
        if (modelID == null) {
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_TEMPLATE_ERROR, "未找到对应的模板ID");
        }
        Map<String, Object> map = DataUtils.beanToMap(reqData);

        if(!map.containsKey("remark")){
            map.put("remark","");
        }

        //  获取模板ID
        WechatTemplateModel wechatTemplateModel = wechatTemplateService.getTemplate(mpID, modelID,reqData.getGroupID(),modelType);
        if (wechatTemplateModel == null) {
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_TEMPLATE_ERROR, "初始化微信模板消息异常");
        }
        logger.debug(() -> "templateID : [ " + wechatTemplateModel.getTemplateID() + " ]");
        map.put("templateID", wechatTemplateModel.getTemplateID());
        //  获取用户openID

        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("mpID", mpID);
        param.put("isSubscribe", 1);
        param.put("openID", reqData.getOpenID());
        String userOpenID = wechatUserService.queryOpenID(param);

        if (StringUtils.isEmpty(userOpenID)) {
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_TEMPLATE_ERROR, "未找到对应的微信用户或用户已经取消关注");
        }
        // 拼接模板消息
        WechatTemplate wechatTemplate = wechatTemplateFatory.newWechatTemplate(null, modelType, modelSubType, null);
        if (wechatTemplate == null) {
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_TEMPLATE_ERROR, "封装微信模板消息异常");
        }
        PropertyPlaceholderHelper strictHelper = new PropertyPlaceholderHelper(
                WechatTemplateConstants.DLD_PLACEHOLDER_PREFIX,
                WechatTemplateConstants.DLD_PLACEHOLDER_SUFFIX,
                WechatTemplateConstants.DLD_DEFAULT, true);
        Properties properties = new Properties();
        properties.putAll(map);
        String templateStr = strictHelper.replacePlaceholders(wechatTemplate.getTemplateJson().toJSONString(),
                properties);

        logger.debug(() -> "MapPlaceholderResolver : [" + templateStr + " ]");


        // 调用服务插入模板队列
        WechatMsgQueueService.WechatQueueReq req = new WechatMsgQueueService.WechatQueueReq();
        //WechatQueueReq = DataUtils.mapToBean(map, WechatQueueReq.getClass());

        req.setOpenID(userOpenID);
        req.setUserID(userID);
        req.setModelID(modelID);
        req.setMpID(mpID);
        req.setParam1(reqData.getParam1());
        req.setTemplateContent(templateStr);
        req.setTemplateType(templateTypeEnum.getModelType());
        req.setTemplateSubType(templateTypeEnum.getModelSubType());
        req.setTemplateID(wechatTemplateModel.getTemplateID());
        req.setOrderKey(UUID.randomUUID().toString());
        req.setGroupID(reqData.getGroupID());
        if(StringUtils.isNotEmpty(reqData.getOrderKey()))
            req.setOrderKey(reqData.getOrderKey());

        WechatMsgQueueService wechatMsgQueueService = rpcClient.getRpcClient(WechatMsgQueueService.class);
        WechatMsgQueueService.WechatQueueRes wechatQueueRes = wechatMsgQueueService.wechatCreateMsgQueue(req);
        WechatTemplateRpcResData wechatTemplateRpcResData = new WechatTemplateRpcResData();

        wechatTemplateRpcResData.setResultInfo(wechatQueueRes.getCode(), wechatQueueRes.getMessage());
        wechatTemplateRpcResData.setItemID(wechatQueueRes.getItemID());
        wechatTemplateRpcResData.setMsgType("WECHAT");
        return wechatTemplateRpcResData;
    }

    protected String gerModelID(String modelType, String modelSubType) {

        if (WechatTemplateConstants.MODEL_TYPE_MODELID_MAP.containsKey(modelType + "_" + modelSubType)) {
            return WechatTemplateConstants.MODEL_TYPE_MODELID_MAP.get(modelType + "_" + modelSubType);
        }
        return null;
    }

    /**
     * 发送短信
     * @param smsContent
     * @param toMobile
     * @return
     */
    private WechatTemplateRpcResData sentSMS(String smsContent, String toMobile){

        logger.debug(() -> "sentSMS : [ 微信消息发送失败 ]");

        WechatTemplateRpcResData wechatTemplateRpcResData = new WechatTemplateRpcResData();
        if(StringUtils.isEmpty(toMobile) || StringUtils.isEmpty(smsContent)) {
            wechatTemplateRpcResData.setResultInfo(ErrorCodes.WECHAT_MP_ERROR, "手机号或短信内容为空");
            wechatTemplateRpcResData.setMsgType("SMS");
            return wechatTemplateRpcResData;
        }

        SemSMSQueueService.SmsQueueReq req = new SemSMSQueueService.SmsQueueReq();
        req.setSmsContent(smsContent);
        req.setSmsType(24);
        req.setToMobile(toMobile);
        SemSMSQueueService semSMSQueueService = rpcClient.getRpcClient(SemSMSQueueService.class);
        SemSMSQueueService.ResInfo res = semSMSQueueService.sendMessage(req);

        wechatTemplateRpcResData.setResultInfo(res.getCode(), res.getMessage());
        wechatTemplateRpcResData.setMsgType("SMS");
        return wechatTemplateRpcResData;
    }



    @Override
    public WechatSendTemplateRes sentWechatTemplateByMQ(WechatSendTemplateReq reqData) {
        String mpID = reqData.getMpID();

        if(StringUtils.isEmpty(mpID)){
            mpID = mpInfoService.queryMpIDAuth(reqData.getGroupID(),reqData.getBrandID(),reqData.getShopID());
        }
        if(StringUtils.isEmpty(mpID)) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MPID_EMPTY ,"未找到对应公众号");
        }

        WechatTemplateTypeEnum templateTypeEnum = reqData.getTemplateType();
        if(templateTypeEnum.getValue() == 0){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"未指定模板消息类型");
        }
        String modelType =templateTypeEnum.getModelType();
        String modelSubType = templateTypeEnum.getModelSubType();

        long userID = reqData.getUserID();

        String modelID = gerModelID(modelType, modelSubType);
        if (modelID == null) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"未找到对应的模板ID");
        }
        Map<String, Object> map = DataUtils.beanToMap(reqData);

        if(!map.containsKey("remark")){
            map.put("remark","");
        }

        //  获取模板ID
        WechatTemplateModel wechatTemplateModel = wechatTemplateService.getTemplate(mpID, modelID,reqData.getGroupID(),modelType);
        if (wechatTemplateModel == null) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"初始化微信模板消息异常");
        }
        logger.debug(() -> "templateID : [ " + wechatTemplateModel.getTemplateID() + " ]");
        map.put("templateID", wechatTemplateModel.getTemplateID());
        //  获取用户openID

        Map<String, Object> param = new HashMap<>();
        param.put("userID", userID);
        param.put("mpID", mpID);
        param.put("isSubscribe", 1);
        param.put("openID", reqData.getOpenID());
        String userOpenID = wechatUserService.queryOpenID(param);
        List <WechatTemplateItem> templateItems = reqData.getTemplateItem();
        List <WxMpTemplateData> collect = templateItems.stream()
                                                       .map( item -> new WxMpTemplateData( item.getType().getName(), item.getValue(), item.getColor() ) )
                                                       .collect( Collectors.toList() );
        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder()
                                                                     .templateId( wechatTemplateModel.getTemplateID() )
                                                                     .toUser( userOpenID )
                                                                     .url( reqData.getUrl() )
                                                                     .data( collect )
                                                                     .build();
        String json = wxMpTemplateMessage.toJson();
        String mqMsg = "{" +
                "\"mpID\":\"" + mpID + "\","+
                "\"param\":" + json +""+
                "}";
        try {
            rabbitTemplate.convertAndSend( rabbitQueueProps.getTemplateMessageExchange(),null ,mqMsg );
        }catch (AmqpException e){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"RabbitMQ发送消息失败");
        }
        return ResultUtil.success(WechatSendTemplateRes.class);
    }

}
