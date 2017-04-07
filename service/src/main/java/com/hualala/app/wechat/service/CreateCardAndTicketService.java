package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/4/6.
 * 创建卡券接口
 * 返回的结果只包含错误和成功，没有null值。
 返回示例：
 responseJson：
 {
    "errcode":0,
    "errmsg":"ok",
    "card_id":"p1Pj9jr90_SQRaVqYI239Ka1erkI"
 }
 */
@Service
public class CreateCardAndTicketService {

    @Autowired
    private HttpApiService httpApiService;
    public JSONObject createCardAndTicket(JSONObject jsonObject,String mpId){
        String url = WechatBaseApi.CREATE_CARD_URL;

        JSONObject responseJson = httpApiService.httpPost(url, jsonObject.toJSONString(), mpId);
        //首先判断 null ：200    然后判断创建是否成功
        if(null == responseJson){
            return ResultUtil.toResultJson(responseJson,false, ErrorCodes.WECHAT_HTTP_FAILED,"http请求失败！");
        }
        String errcode = responseJson.getString( WechatBaseApi.MP_ERRCODE);
        if(!"0".equals(errcode)){
            return ResultUtil.toResultJson(responseJson,false,null, WechatErrorCode.wechatError.get(errcode));
        }
        return ResultUtil.toResultJson(responseJson,true,ErrorCodes.WECHAT_SUCCESS_CODE,"");
    }


}
