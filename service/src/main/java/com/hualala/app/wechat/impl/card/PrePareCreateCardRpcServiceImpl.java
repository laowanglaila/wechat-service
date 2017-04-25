package com.hualala.app.wechat.impl.card;

import com.hualala.app.wechat.CreateCardCouponRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.PrePareCreateCardRpcService;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.model.card.CouponModel;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/25.
 */
@Service
public class PrePareCreateCardRpcServiceImpl implements PrePareCreateCardRpcService {

    @Autowired
    private MpInfoService mpInfoService;
    @Autowired
    private WechatMpMapper wechatMpMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;
    @Override
    public CardCouponResData createCoupon(CouponReqData couponReqData) {
//判断mpID,没有则调方法获取
        String mpID = couponReqData.getMpID();
        if (StringUtils.isBlank(mpID)) {
            Long brandID = couponReqData.getBrandID();
            Long groupID = couponReqData.getGroupID();
            Long shopID = couponReqData.getShopID();
            if (null != brandID || null != groupID) {
                return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(groupID, brandID,shopID);
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }
        //groupID
        Long groupID = null;
        groupID = couponReqData.getGroupID();
        if (groupID == null ){
            Map<String, Object> params = new HashMap<>();
            params.put("mpID",mpID);
            List<Map<String, Object>> maps = wechatMpMapper.queryByParams(params);
            if (maps.size() > 0){
                groupID = (Long)maps.get(0).get("groupID");
            }else {
                return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_GROUP_ID_NULL, "获取GroupID失败！");
            }
        }
        couponReqData.setGroupID(groupID);
        couponReqData.setMpID(mpID);
        //cardKey
        if (StringUtils.isBlank(couponReqData.getCardKey())){
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }
        //title
        if (StringUtils.isBlank(couponReqData.getTitle())){
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_TITLE_NULL, "参数title为空！");
        }
        //cardType
        if (StringUtils.isBlank(couponReqData.getCardType())){
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_TYPE_NULL, "参数cardType为空！");
        }

        CouponModel couponModel = DataUtils.copyProperties(couponReqData, CouponModel.class);

        couponModelMapper.insert(couponModel);

        CardCouponResData cardCouponResData = new CardCouponResData();
        cardCouponResData.setCardKey(couponReqData.getCardKey());
        cardCouponResData.setResultInfo(ErrorCodes.WECHAT_SUCCESS_CODE,"请求成功");
        return cardCouponResData;
    }
}
