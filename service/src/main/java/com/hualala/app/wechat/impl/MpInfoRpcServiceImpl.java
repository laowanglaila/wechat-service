package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.MpInfoRpcService;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.model.WechatMpModel;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(StringUtils.isEmpty(reqData.getAppID())){
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


        List<WechatMpModel> list = wechatMpMapper.queryList(param);
        List<MpInfoResData> mpInfoLst = DataUtils.copyList(list, MpInfoResData.class);
        MpInfoQueryResData mpInfoQueryResData = new MpInfoQueryResData();
        mpInfoQueryResData.setMpInfoResDataList(mpInfoLst);
        return mpInfoQueryResData;
    }
}
