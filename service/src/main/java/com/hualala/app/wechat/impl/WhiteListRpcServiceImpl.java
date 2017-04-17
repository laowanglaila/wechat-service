package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.WhiteListRpcService;
import com.hualala.app.wechat.service.BaseHttpService;
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
            //todo 通过上面三个属性获取mpID，调用方法待定；
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new ResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }

        String[] openID = reqData.getOpenID();
        String[] userName = reqData.getUserName();

        HashMap<String, Object> params = new HashMap<>();
        if(userName.length == 0 && openID.length == 0 ){
            return new ResData().setResultInfo(ErrorCodes.WECHAT_ARGS_ERROR, "username或者openID不能为空！");
        }
        if(openID != null && openID.length > 0){
            params.put("openid",openID);
        }
        if (userName != null && userName.length > 0){
            params.put("username",userName);
        }

        //加入微信白名单
        JSONObject jsonObject = baseHttpService.setWhiteList(params, mpID);


        return ResultUtil.getResultInfoBean(jsonObject,ResData.class);
    }
}
