package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.ErrorCodes;
import com.hualala.app.wechat.WhiteListRpcService;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by renjianfei on 2017/4/14.
 * 通过openid或者username将微信账号加入测试白名单
 */
@Service
public class WhiteListRpcServiceImpl implements WhiteListRpcService {
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private MpInfoService mpInfoService;
    @Override
    public ResData addToWhiteList(ReqData reqData) {
        //判断mpID,没有则调方法获取
        String mpID = reqData.getMpID();
        if (StringUtils.isBlank(mpID)) {
            String brandID = reqData.getBrandID();
            String groupID = reqData.getGroupID();
            String shopID = reqData.getShopID();
            if (StringUtils.isBlank(brandID) || StringUtils.isBlank(groupID)) {
                return new ResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(Long.parseLong(groupID), Long.parseLong(brandID), Long.parseLong(shopID));
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new ResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }

        String openID = reqData.getOpenID();
        String userName = reqData.getUserName();

        HashMap<String, Object> params = new HashMap<>();
        if(StringUtils.isBlank(openID) && StringUtils.isBlank(userName)){
            return new ResData().setResultInfo(ErrorCodes.WECHAT_ARGS_ERROR, "username或者openID不能为空！");
        }
        if(StringUtils.isNotBlank(openID)){
            String[] split = openID.split(",");
            params.put("openid",split);
        }
        if (StringUtils.isNotBlank(userName)){
            String[] split = userName.split(",");
            params.put("username",split);
        }

        //加入微信白名单
        JSONObject jsonObject = baseHttpService.setWhiteList(params, mpID);


        return ResultUtil.getResultInfoBean(jsonObject,ResData.class);
    }
}
