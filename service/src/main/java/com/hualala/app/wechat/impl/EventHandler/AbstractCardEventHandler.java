package com.hualala.app.wechat.impl.EventHandler;

import com.hualala.app.crm.bean.card.VoucherCardReq;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.crm.service.CardInfoService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.impl.EventHandler.bean.UserForm;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.app.wechat.sdk.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import com.hualala.core.client.BaseRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

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

    protected VoucherCardRes processCardNotBeenExist(String fromUserName,String userCardCode, BaseInfoModel baseInfoModel){
        return this.processCardNotBeenExist( null,fromUserName,userCardCode,baseInfoModel );
    }

    protected VoucherCardRes processCardNotBeenExist(WxMpMemberCardUserInfoResult userInfo, String fromUserName, String userCardCode, BaseInfoModel baseInfoModel) {
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
        if (userInfo != null){
            HashMap<String, String> collect = Stream.of( userInfo.getUserInfo().getCommonFieldList() )
                                                    .collect( HashMap <String, String>::new,
                                                             (map, item) -> map.put( item.getName(), item.getValue() ),
                                                             (map, map1) -> map.putAll( map1 ) );
            String mobile = collect.get( UserForm.USER_FORM_INFO_FLAG_MOBILE.name() );
            String birthday = collect.get( UserForm.USER_FORM_INFO_FLAG_BIRTHDAY.name() );
            String name = collect.get( UserForm.USER_FORM_INFO_FLAG_NAME.name());
            String sex = collect.get( UserForm.USER_FORM_INFO_FLAG_SEX.name() );
            if (StringUtils.isNotBlank( birthday ))
                voucherCardReq.setCustomerBirthday( birthday );
            if (StringUtils.isNotBlank( sex ))
                voucherCardReq.setCustomerSex( "MALE".equals( sex )||"男".equals( sex )?1:0 );
            if (StringUtils.isNotBlank( mobile ))
                voucherCardReq.setCustomerMobile( mobile );
          if (StringUtils.isNotBlank( name ))
           voucherCardReq.setCustomerName( name );
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
