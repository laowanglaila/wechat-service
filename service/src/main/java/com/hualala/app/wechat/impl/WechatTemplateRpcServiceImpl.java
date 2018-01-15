package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatTemplateRpcService;
import com.hualala.app.wechat.WechatTemplateTypeEnum;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.common.RedisKeys;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.config.RabbitQueueProps;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.mapper.sem.TemplateMessageModelMapper;
import com.hualala.app.wechat.model.WechatTemplateModel;
import com.hualala.app.wechat.model.mp.MpInfoCache;
import com.hualala.app.wechat.model.sem.TemplateMessageModel;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.bean.template.WxMpTemplateData;
import com.hualala.app.wechat.sdk.mp.bean.template.WxMpTemplateMessage;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.WechatTemplateService;
import com.hualala.app.wechat.service.user.WechatUserService;
import com.hualala.app.wechat.util.MD5Util;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.template.WechatTemplate;
import com.hualala.app.wechat.util.template.WechatTemplateConstants;
import com.hualala.app.wechat.util.template.WechatTemplateFatory;
import com.hualala.core.app.Logger;
import com.hualala.core.client.BaseRpcClient;
import com.hualala.core.utils.DataUtils;
import com.hualala.message.SemSMSQueueService;
import com.hualala.message.WechatMsgQueueService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private WxGroupMpService wxMpService;

    protected Logger logger = Logger.of(WechatTemplateRpcServiceImpl.class);

    @Value( "${env.m.domain}" )
    private String domain ;

    private final String ORDER_URL = "/order/detail.htm";
    private final String CRM_URL = "/crm/vip_details.htm";
    private final String GIFT_URL = "/user/event.htm";
    private final String QUEUE_URL = "/user/queueno.htm";
    private final String CRM_TRANS_URL = "/crm/vip_trans.htm";
    private final String ASSESSMENT_REPLY =  "/user/remarks.htm";
    // 商城的订单详情
    private final String STORE_ORDER_URL = "/mall/order-detail.htm";

    private final String INVOICE_URL = "/receipt/detail.htm";
    private final String INVOICE_LST_URL = "/receipt/my-receipt.htm";
    private final String BOOK_ORDER_URIL = "/corder/detail.htm";



    public static final String ORDER_TYPE = "order";

    public static final String CRM_TYPE = "crm";

    public static final String GIFT_TYPE = "gift";

    public static final String QUEUE_TYPE = "queue";
    // 顾客评价回复
    public static final String ASSESSMENT_TYPE = "assessment";

    public static final String INVOICE_TYPE = "invoice";

    public static final String SUBMIT_SUBTYPE = "submit";

    public static final String CHECK_SUBTYPE = "check";

    public static final String RETURN_SUBTYPE = "return";

    public static final String REFUND_SUBTYPE = "refund";

    public static final String ALARM_SUBTYPE = "alarm";

    public static final String PAID_ALARM = "paidAlarm";

    public static final String ORDER_BOOK = "book";

    public static final String TRANS_SUBTYPE = "trans";

    // 积分领取通知
    public static final String GIFT_ALARM_CRMPOINT = "crmPoint";
    // 会员储值领取通知
    public static final String GIFT_ALARM_CRMMONEY = "crmMoney";
    // 代金券领取通知
    public static final String GIFT_ALARM_GIFT = "giftAlarm";
    // 会员权益包领取通知
    public static final String GIFT_ALARM_CRMCUSTOMER = "crmCustomer";
    // 会员推荐奖励提醒
    public static final String GIFT_ALARM_CRMBRANCH = "crmBranch";
    // 会员权益包变动提醒
    public static final String GIFT_CRMCUSTOMER_CHANGE = "crmCustomerChange";
    // 会员积分清零
    public static final String GIFT_POINT_INIT = "crmPointInit";
    // 顾客评价回复
    public static final String ASSESSMENT_ALARM_REPLY = "asReply";




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
    @Autowired
    private StringRedisTemplate skuRedisTemplate;

    @Autowired
    private TemplateMessageModelMapper templateMessageModelMapper;

    @Override
    public WechatTemplateRpcResData sentWechatTemplate(WechatTemplateRpcReqData reqData) {

        String mpID = reqData.getMpID();

        if(StringUtils.isEmpty(mpID)){
            mpID = mpInfoService.queryMpIDAuth(reqData.getGroupID(),reqData.getBrandID(),reqData.getShopID());
        }
        if(StringUtils.isEmpty(mpID)) {
            return new WechatTemplateRpcResData().setResultInfo( ErrorCodes.WECHAT_MPID_EMPTY, "未找到对应公众号");
        }

        WechatTemplateTypeEnum modelTypeEnum = reqData.getTemplateType();
        if(modelTypeEnum.getValue() == 0){
            return new WechatTemplateRpcResData().setResultInfo(ErrorCodes.WECHAT_TEMPLATE_ERROR, "未指定模板消息类型");
        }
        String modelType =modelTypeEnum.getModelType();
        String modelSubType = modelTypeEnum.getModelSubType();

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
        req.setTemplateType(modelTypeEnum.getModelType());
        req.setTemplateSubType(modelTypeEnum.getModelSubType());
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

        MpInfoCache mpInfo = null;
        try {
            mpInfo = mpInfoService.getMpInfoByMpID( mpID );
        } catch (WechatInnerException e) {
            logger.error( "获取groupID失败",e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_COMMON_ERROR,e.getMessage() );
        }
        Integer groupID = mpInfo.getGroupID();
        WechatTemplateTypeEnum modelTypeEnum = reqData.getTemplateType();
        if(modelTypeEnum.getValue() == 0){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"未指定模板消息类型");
        }
        String modelType =modelTypeEnum.getModelType();
        String modelSubType = modelTypeEnum.getModelSubType();

        long userID = reqData.getUserID();

        String modelID = gerModelID(modelType, modelSubType);
        if (modelID == null) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"未找到对应的模板ID");
        }


        //  获取模板ID
        WechatTemplateModel wechatTemplateModel = wechatTemplateService.getTemplate(mpID, modelID,groupID,modelType);
        if (wechatTemplateModel == null) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"初始化微信模板消息异常");
        }
        logger.debug(() -> "templateID : [ " + wechatTemplateModel.getTemplateID() + " ]");
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
        String param1 = reqData.getParam1();
        String orderKey = reqData.getOrderKey();
        String url = reqData.getUrl();
        if (StringUtils.isBlank( url )) {
            url = this.generateUrl( mpID, groupID, modelTypeEnum, param1, orderKey );
        } else if (url != null && !url.contains( "http" )){
            url = domain + url;
        }
        //todo mpID
        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder()
             .templateId( wechatTemplateModel.getTemplateID() )
             .toUser( userOpenID )
             .url( url )
             .data( collect )
             .build();
//        try {
//            String s = wxMpService.getTemplateMsgService( mpID ).sendTemplateMsg( wxMpTemplateMessage );
//            System.out.println(s);
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
        this.sendMessageToMq( mpID,userOpenID, wxMpTemplateMessage );
        return ResultUtil.success(WechatSendTemplateRes.class);
    }
    private String generateUrl(String mpID,
                               Integer groupID,
                               WechatTemplateTypeEnum templateTypeEnum,
                               String param1,
                               String orderKey) {
        String url = null;
        switch (templateTypeEnum){
            case TEMPLATE_ENUM_ORDER:
                url =  domain + ORDER_URL+"?mpid="+mpID+"&g="+groupID +"&i="+orderKey;
                break;
            case TEMPLATE_ENUM_ORDER_BOOK:
                url = domain +  BOOK_ORDER_URIL+"?mpid="+mpID+"&g="+groupID +"&key="+orderKey;
                break;
            case TEMPLATE_ENUM_QUEUE:
                url = null;
                break;
            case TEMPLATE_ENUM_INVOICE:
                if("-1".equals(param1)) {
                    url = domain +  INVOICE_LST_URL + "?mpid=" + mpID + "&g=" + groupID;
                } else {
                    // 电子发票
                    url = domain +  INVOICE_URL + "?i=" + param1 + "&mpid=" + mpID + "&g=" + groupID;
                }
                break;
            case TEMPLATE_ENUM_CRM_REG:
                //TODO
                break;
            case TEMPLATE_ENUM_CRM_GIFT:
                break;
            case TEMPLATE_ENUM_CRM_TRANS:
                break;
            case TEMPLATE_ENUM_CRM_CUSTOMER:
                //新版会员权益
//                url =  GIFT_URL+"?mpid="+mpID+"&g="+groupID;
                break;
            case TEMPLATE_ENUM_ASSESSMENT:
                //用餐评价提醒
                url = domain + ASSESSMENT_REPLY + "i="+param1+"&mpid="+mpID+"&g="+groupID;
                break;
            default:
                url = null;
        }

        return url;
    }
    private String generateUrl(String mpID,
                             Integer groupID,
                             String modelType,
                             String modelSubType,
                             String param1,
                             String orderKey) {
        String url = null;
        if(ORDER_TYPE.equals(modelType)){
            // 订单状态
            url =  domain + ORDER_URL+"?mpid="+mpID+"&g="+groupID +"&i="+orderKey;

            if(ORDER_BOOK.equals(modelSubType)){
                url =  domain + BOOK_ORDER_URIL+"?mpid="+mpID+"&g="+groupID +"&key="+orderKey;
            } else if("50".equals(param1) || "51".equals(param1)) {
                url =  domain + STORE_ORDER_URL+"?mpid="+mpID+ "&g="+groupID+"&k="+orderKey;
            } else if(PAID_ALARM.equals(modelSubType)){
                url = null;
            }

        }else if(CRM_TYPE.equals(modelType)){
            //会员
            url =  domain + CRM_URL+"?mpid="+mpID+"&g="+groupID;
        }else if(GIFT_TYPE.equals(modelType)){
            // 会员充值/会员积分
            if (GIFT_ALARM_CRMMONEY.equals(modelSubType)
                    || GIFT_ALARM_CRMPOINT.equals(modelSubType)
                    ) {
                url =  domain + CRM_URL+"?mpid="+mpID+"&g="+groupID;
            } else if (GIFT_ALARM_CRMBRANCH.equals(modelSubType)) {

            } else if (GIFT_ALARM_GIFT.equals(modelSubType)) {
                // 我的代金券
                url =  domain + GIFT_URL+"?mpid="+mpID+"&g="+groupID;
            } else if (GIFT_CRMCUSTOMER_CHANGE.equals(modelSubType)) {
                // 会员权益变动
            } else {
                // 我的代金券
                url =  domain + GIFT_URL+"?mpid="+mpID+"&g="+groupID;
            }
        }else if(QUEUE_TYPE.equals(modelType)){
            // 排号详情页面
            //url =  QUEUE_URL+"?sc=wechat&mpid="+mpID+"&g="+groupID;
            //http://local.m.hualala.com/table/num.htm?s=77875&g=5
        } else if (ASSESSMENT_TYPE.equals(modelType)) {
            url =  domain + ASSESSMENT_REPLY+"?i="+param1+"&mpid="+mpID+"&g="+groupID;

        } else if (INVOICE_TYPE.equals(modelType)) {
            if("-1".equals(param1)) {
                url =  domain + INVOICE_LST_URL + "?mpid=" + mpID + "&g=" + groupID;
            } else {
                // 电子发票
                url =  domain + INVOICE_URL + "?i=" + param1 + "&mpid=" + mpID + "&g=" + groupID;
            }
        }
        return url;
    }

    private void sendMessageToMq(String mpID,String openID, WxMpTemplateMessage wxMpTemplateMessage) {
        String json = wxMpTemplateMessage.toJson();
        String mqMsg = "{" +
                "\"mpID\":\"" + mpID + "\","+
                "\"param\":" + json +""+
                "}";
        Integer status = 0;
        logger.info( ()-> mqMsg );
        try {
            rabbitTemplate.convertAndSend( rabbitQueueProps.getTemplateMessageExchange(),null ,mqMsg );
            status = 1;
        }catch (AmqpException e){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_TEMPLATE_ERROR ,"RabbitMQ发送消息失败");
        }finally {
//            this.insertDBAndRedis( mpID, openID, mqMsg ,status);
        }
    }

    private void insertDBAndRedis(String mpID, String openID, String mqMsg,Integer status) {
        TemplateMessageModel templateMessageModel = new TemplateMessageModel();
        templateMessageModel.setMpID( mpID );
        templateMessageModel.setOpenID( openID );
        templateMessageModel.setMessage( mqMsg );
        templateMessageModel.setStatus( status );
        templateMessageModelMapper.insertSelective( templateMessageModel );
        Long itemID = templateMessageModel.getItemID();
        String sequence = MD5Util.MD5Encode( mqMsg );
        BoundValueOperations<String, String> ops = skuRedisTemplate.boundValueOps( RedisKeys.WEHCHAT_MQ_MESSAGE_KEY + sequence );
        ops.set( ""+itemID );
    }

}
