package com.hualala.app.wechat.common;

/**
 * Created by xkia on 2017/3/21.
 */
public interface WechatBaseApi {
     String AUTHORIZE_1 = "1";

     String ROOT = "https://api.weixin.qq.com/cgi-bin";

     String GET_ACCESS_TOKEN = ROOT + "/token?grant_type=client_credential";

     String API_COMPONENT_TOKEN = ROOT + "/component/api_component_token";

     String API_AUTHORIZER_TOKEN = ROOT + "/component/api_authorizer_token";

    //微信卡券上传背景图片
     String API_UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
    //微信上传临时素材
     String API_UPLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";
    //微信卡券创建卡券
     String CREATE_CARD_URL = "https://api.weixin.qq.com/card/create";
    //微信卡券设置自助核销
     String SET_SELF_CONSUME = "https://api.weixin.qq.com/card/selfconsumecell/set";
    //微信卡券查看卡券详情/审核状态
     String GET_CARD_INFO = "https://api.weixin.qq.com/card/get";
    //微信卡券批量查看卡券id
     String BATCHGET_CARD_ID = "https://api.weixin.qq.com/card/batchget";
    //微信卡券修改库存
     String MODIFY_CARD_SKU = "https://api.weixin.qq.com/card/modifystock";
    //微信卡券删除卡券
     String DELETE_CARD = "https://api.weixin.qq.com/card/delete";
    //微信卡券设置卡券失效接口
     String UNAVAILABLE_CARD = "https://api.weixin.qq.com/card/code/unavailable";
    //微信卡券查询code接口
     String GET_CODE_STATUS = "https://api.weixin.qq.com/card/code/get";
    //微信卡券获取用户领取卡券列表
     String GET_CARD_LIST = "https://api.weixin.qq.com/card/user/getcardlist";
    //设置测试白名单接口
     String SET_WHITE_LIST = "https://api.weixin.qq.com/card/testwhitelist/set";
    //导入微信code码
     String IMPORT_CARD_CODE = "http://api.weixin.qq.com/card/code/deposit";
    //获取导入code码数量
     String GET_DEPOSIT_COUNT = "http://api.weixin.qq.com/card/code/getdepositcount";
    //支持开发者调用该接口查询code导入微信后台的情况
     String CHECK_CARD_CODE = "http://api.weixin.qq.com/card/code/checkcode";
    //获取息群发卡券图文消
     String GET_HTML_MESSAGE = "https://api.weixin.qq.com/card/mpnews/gethtml";
    //核销code接口
     String CONSUME_CARD_CODE = "https://api.weixin.qq.com/card/code/consume";
    //解码code接口
     String DECRYPT_CARD_CODE = "https://api.weixin.qq.com/card/code/decrypt";
    //微信会员卡微信激活接口
     String ACTIVATE_CARD = "https://api.weixin.qq.com/card/membercard/activate";
    //微信会员卡更新会员信息
     String UPDATE_MEMBER_INFO = "https://api.weixin.qq.com/card/membercard/updateuser";
    //微信会员卡拉取会员信息（积分）
     String GET_MEMBER_INFO = "https://api.weixin.qq.com/card/membercard/userinfo/get";
    //微信会员卡修改卡面信息
     String UPDATE_CARD_INFO = "https://api.weixin.qq.com/card/update";
    //设置一键激活时用户界面的字段
     String SET_ACTIVATE_USERFORM = "https://api.weixin.qq.com/card/membercard/activateuserform/set";
    //获取一键激活时用户填入的信息
     String GET_ACTIVATE_USERFORM = "https://api.weixin.qq.com/card/membercard/activatetempinfo/get";
    //更改code接口（在转赠优惠券时使用）
     String UPDATE_CARD_CODE = "https://api.weixin.qq.com/card/code/update";
    //获取微信二维码
     String CREATE_QR_CODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    // 添加微信模板ID
     String API_ADD_TEMPLATE = ROOT + "/template/api_add_template";
    // 获取ApiTicket
     String GET_API_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    // 支付即会员、支付赠券
     String PAY_GIFT_CARD = "https://api.weixin.qq.com/card/paygiftcard/add";
    // 客服消息接口
     String MESSAGE_CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    // 网页授权获取用户信息
     String GET_WECHAT_USERINFO = "https://api.weixin.qq.com/sns/userinfo";

}
