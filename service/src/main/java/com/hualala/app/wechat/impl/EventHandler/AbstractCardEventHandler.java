package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.card.VoucherCardReq;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.crm.service.CardInfoService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.core.client.BaseRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by renjianfei on 2017/12/15.
 */
@Slf4j
public abstract class AbstractCardEventHandler extends BaseEventCardEventHandler {
    @Autowired
    private UserModelMapper userModelMapper;
    @Autowired
    protected BaseRpcClient baseRpcClient;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    protected BaseInfoModel getBaseInfoModel(String cardId) {
        BaseInfoModelQuery baseInfoModelQuery = new BaseInfoModelQuery();
        baseInfoModelQuery.createCriteria().andCardIDEqualTo( cardId );
        List<BaseInfoModel> baseInfoModels = baseInfoModelMapper.selectByExample( baseInfoModelQuery );
        BaseInfoModel baseInfoModel = null;
        if (baseInfoModels != null && baseInfoModels.size() > 0) {
            baseInfoModel = baseInfoModels.get( 0 );
        }else {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_NONE_CARD_RELATION_MAPPING ,"没有找到卡券信息，baseInfoModel is null");
        }
        Long hualalaCardID = baseInfoModel.getHualalaCardID();
        if ( hualalaCardID == null || hualalaCardID == 0){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_NONE_CARD_RELATION_MAPPING ,"没有绑定任何哗啦啦卡券，hualalaCardID is null");
        }
        return baseInfoModel;
    }

    protected VoucherCardRes processCardNotBeenExist(String mobile, String fromUserName,String userCardCode, BaseInfoModel baseInfoModel) {
        CardInfoService rpcClient = baseRpcClient.getRpcClient( CardInfoService.class );
        VoucherCardReq voucherCardReq = new VoucherCardReq();
        voucherCardReq.setMpID( baseInfoModel.getMpID() );
        voucherCardReq.setGroupID( baseInfoModel.getGroupID() );
        voucherCardReq.setWechatCardKey( baseInfoModel.getCardKey() );
        voucherCardReq.setCardTypeID( baseInfoModel.getHualalaCardID() );
        voucherCardReq.setWechatCardCode( userCardCode );
        voucherCardReq.setSourceWay( true );
        voucherCardReq.setSourceType( 30 );
        voucherCardReq.setShopWeixinID( fromUserName );
        if (mobile != null){
            voucherCardReq.setCustomerMobile( mobile );
        }
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria().andOpenidEqualTo( fromUserName );
        List <UserModel> userModels = userModelMapper.selectByExample( userModelQuery );
        if (userModels != null && userModels.size() > 0) {
            UserModel userModel = userModels.get( 0 );
            Long userID = userModel.getUserID();
            if(userID != null && -1 != userID){
                UserModelQuery userModelQuery1 = new UserModelQuery();
                userModelQuery1.createCriteria().andUserIDEqualTo( userID ).andMpIDEqualTo( "hualala_com" );
                List <UserModel> userModels1 = userModelMapper.selectByExample( userModelQuery1 );
                if (userModels1 != null && userModels1.size() > 0) {
                    UserModel userModel1 = userModels1.get( 0 );
                    String openid = userModel1.getOpenid();
                    voucherCardReq.setWeixinID( openid );
                }
            }
        }
        return rpcClient.reverseVoucherCardAssociation( voucherCardReq );
    }

}
