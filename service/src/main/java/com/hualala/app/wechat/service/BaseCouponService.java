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
 * 微信卡券通用接口
 */
@Service
public class BaseCouponService {

    @Autowired
    private HttpApiService httpApiService;

    /**
     * 通用的httppost请求方法返回标准的result
     * 适合errcode =“0”代表成功的请求使用
     */

    public JSONObject commonHttpPost(String url,JSONObject jsonObject, String mpID){
        JSONObject responseJson = httpApiService.httpPost(url, jsonObject.toJSONString(), mpID);
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
    public JSONObject createCardAndTicket(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.CREATE_CARD_URL,jsonObject,mpId);
    }


    /**
     * Created by renjianfei on 2017/4/7.
     * 查询code接口可以查询当前code是否可以被核销并检查code状态。当前可以被定位的状态为正常、已核销、转赠中、已删除、已失效和无效code。
     */
    public JSONObject getCodeStatus(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.GET_CODE_STATUS,jsonObject,mpId);
    }
    /**
     * Created by renjianfei on 2017/4/7.
     * 用于获取用户卡包里的，属于该appid下所有可用卡券，包括正常状态和未生效状态。
     */
    public JSONObject getCardList(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.GET_CARD_LIST,jsonObject,mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 查询某个card_id的创建信息、审核状态以及库存数量。
     */
    public  JSONObject getCardInfo(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.GET_CARD_INFO,jsonObject,mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 批量查看卡券详情
     */
    public  JSONObject getBatchCardInfo(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.BATCHGET_CARD_INFO,jsonObject,mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 增减某张券的库存
     */
    public  JSONObject setCardSku(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.MODIFY_CARD_SKU,jsonObject,mpId);
    }
     /**
     * reated by renjianfei on 2017/4/7.
     * 删除一个优惠券或者会员卡
     */
    public  JSONObject deleteCard(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.DELETE_CARD,jsonObject,mpId);
    }
     /**
     * reated by renjianfei on 2017/4/7.
     * 设置优惠券或者会员卡为失效状态
     */
    public  JSONObject setCardUnavailable(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.UNAVAILABLE_CARD,jsonObject,mpId);
    }
     /**
     * reated by renjianfei on 2017/4/7.
     * 获取测试白名单
     */
    public  JSONObject getWhiteList(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.GET_WHITE_LIST,jsonObject,mpId);
    }

     /**
     * reated by renjianfei on 2017/4/7.
     * 导入微信code码
      * 单次导入微信卡券后台的自定义code，上限为100个
     */
    public  JSONObject importCardCode(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.IMPORT_CARD_CODE,jsonObject,mpId);
    }
     /**
     * reated by renjianfei on 2017/4/7.
     * 查询code导入微信后台成功的数目
     */
    public  JSONObject getDepositCount(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.GET_DEPOSIT_COUNT,jsonObject,mpId);
    }
     /**
     * reated by renjianfei on 2017/4/7.
     * 查询code导入微信后台的情况
     */
    public  JSONObject checkCardCode(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.CHECK_CARD_CODE,jsonObject,mpId);
    }
     /**
     * reated by renjianfei on 2017/4/7.
     * 获取息群发卡券图文消
     */
    public  JSONObject getHtmlMessage(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.GET_HTML_MESSAGE,jsonObject,mpId);
    }

     /**
     * reated by renjianfei on 2017/4/7.
     * 核销code接口
     */
    public  JSONObject consumeCardCode(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.CONSUME_CARD_CODE,jsonObject,mpId);
    }
    /**
     * 修改卡面信息
     * 支持更新所有卡券类型的部分通用字段及特殊卡券（会员卡、飞机票、电影票、会议门票）中特定字段的信息。
     */
    public JSONObject updateCardInfo(JSONObject jsonObject, String mpID){
        String url = WechatBaseApi.UPDATE_CARD_INFO;
        return this.commonHttpPost(url,jsonObject,mpID);
    }



//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /**
     * Created by renjianfei on 2017/4/7.
     * 优惠券微信接口
     */

    /**
     * Created by renjianfei on 2017/4/7.
     * 微信自助核销卡券接口
     */
    public JSONObject setSelfConsume(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.SET_SELF_CONSUME,jsonObject,mpId);
    }
    /**
     * Created by renjianfei on 2017/4/7.
     * 更改code接口（在转赠优惠券时使用）
     */
    public JSONObject updateCardCode(JSONObject jsonObject,String mpId){
        return this.commonHttpPost(WechatBaseApi.UPDATE_CARD_CODE,jsonObject,mpId);
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 激活会员卡接口
     */
    public JSONObject activate(JSONObject jsonObject, String mpID){
        String url = WechatBaseApi.ACTIVATE_CARD;
        return this.commonHttpPost(url,jsonObject,mpID);
    }
    /**
     * 更新会员信息
     */
    public JSONObject updateMemberInfo(JSONObject jsonObject, String mpID){
        String url = WechatBaseApi.UPDATE_MEMBER_INFO;
        return this.commonHttpPost(url,jsonObject,mpID);
    }
    /**
     * 拉取会员信息（积分）
     */
    public JSONObject getMemberInfo(JSONObject jsonObject, String mpID){
        String url = WechatBaseApi.GET_MEMBER_INFO;
        return this.commonHttpPost(url,jsonObject,mpID);
    }

    /**
     * 获取一键激活时用户填入的信息
     */
    public JSONObject setActivateUserForm(JSONObject jsonObject, String mpID){
        String url = WechatBaseApi.GET_ACTIVATE_USERFORM;
        return this.commonHttpPost(url,jsonObject,mpID);
    }
}
