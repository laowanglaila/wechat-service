package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.MpInfoRpcService;
import com.hualala.app.wechat.MpTypeEnum;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.model.WechatMpModel;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xkia on 2017/3/20.
 */
@Service
public class MpInfoRpcServiceImpl implements MpInfoRpcService {

    @Autowired
    private WechatMpMapper wechatMpMapper;

    @Override
    public MpInfoQueryResData queryMpInfo(MpInfoQueryReqData reqData) {

        Map<String, Object> param = new HashMap<>();
        if(StringUtils.isNotEmpty(reqData.getAppID())){
            param.put("appID",reqData.getAppID());
        }
        if(StringUtils.isNotEmpty(reqData.getMpID())){
            param.put("mpID",reqData.getMpID());
        }
        if(StringUtils.isNotEmpty(reqData.getMpName())){
            param.put("mpName",reqData.getMpName());
        }
        if(reqData.getMpType() != null && reqData.getMpType().getValue() != 0){
            param.put("mpType",reqData.getMpType().getValue());
        }
        if(reqData.getGroupID() > 0){
            param.put("groupID",reqData.getGroupID());
        }
        if(reqData.getBrandID() > 0){
            param.put("brandID",reqData.getBrandID());
        }
        if(reqData.getShopID() > 0){
            param.put("shopID", reqData.getShopID());
        }
        if (reqData.getPageSize() == 0){
            param.put( "pageSize",10 );
        }else {
            param.put( "pageSize",reqData.getPageSize() );
        }
        if (reqData.getPageNo() == 0){
            param.put( "offset",0 );
        }else {
            param.put( "offset",reqData.getPageSize() * (reqData.getPageNo() - 1) );
        }

        List<WechatMpModel> list = wechatMpMapper.queryList(param);
        List <MpInfoResData> collect = copyList( list );
        MpInfoQueryResData mpInfoQueryResData = new MpInfoQueryResData();
        mpInfoQueryResData.setMpInfoResDataList(collect);
        return mpInfoQueryResData;
    }

    private List <MpInfoResData> copyList(List <WechatMpModel> list) {
        return list.stream().map( model -> {
            MpInfoResData mpInfoResData = new MpInfoResData();
            mpInfoResData.setMpID( model.getMpID() );
            mpInfoResData.setGhID( model.getGhID() );
            mpInfoResData.setMpName( model.getMpName() );
            mpInfoResData.setMpType( MpTypeEnum.valueOf( model.getMpType() ) );
            mpInfoResData.setToken( model.getToken() );
            mpInfoResData.setAppID( model.getAppID() );
            mpInfoResData.setAppSecret( model.getAppSecret() );
            mpInfoResData.setEncodingAESKey( model.getEncodingAESKey() );
            mpInfoResData.setWeixinURL( model.getWeixinURL() );
            mpInfoResData.setHeadImg( model.getHeadImg() );
            mpInfoResData.setQrCodeURL( model.getQrCodeURL() );
            mpInfoResData.setMenuJson( model.getMenuJson() );
            mpInfoResData.setGroupID( model.getGroupID());
            mpInfoResData.setBrandID( model.getBrandID() );
            mpInfoResData.setShopID( model.getShopID() );
            mpInfoResData.setCustomerWithoutBindMobile( model.getCustomerWithoutBindMobile().toString() );
            mpInfoResData.setSubscribeToCcustomer( model.getSubscribeToCcustomer().toString() );
            mpInfoResData.setTableMsgTemplate( model.getTableMsgTemplate() );
            mpInfoResData.setAlias( model.getAlias() );
            mpInfoResData.setAuthorize( model.getAuthorize().toString() );
            mpInfoResData.setAuthorizerRefreshToken( model.getAuthorizerRefreshToken() );
            mpInfoResData.setOauth( model.getOauth().toString() );
            mpInfoResData.setWechatEndDate( model.getWechatEndDate() );
            mpInfoResData.setIsActiveUse( model.getIsActiveUse().toString() );
            return mpInfoResData;
        } ).collect( Collectors.toList() );
    }

    @Override
    public MpInfoQueryResData selectMp(MpInfoSelectReqData reqData) {
        Map<String, Object> param = new HashMap<>();
        if(StringUtils.isNotEmpty(reqData.getMpID())){
            param.put("mpID",reqData.getMpID());
        }
        List<WechatMpModel> list = wechatMpMapper.select(param);
        List<MpInfoResData> mpInfoLst = DataUtils.copyList(list, MpInfoResData.class);
        MpInfoQueryResData mpInfoQueryResData = new MpInfoQueryResData();
        mpInfoQueryResData.setMpInfoResDataList(mpInfoLst);
        return mpInfoQueryResData;
    }
}
