package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.PrePareCreateCardRpcService;
import com.hualala.app.wechat.PrePareQueryCardRpcService;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.mapper.card.AdvancedModelMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.*;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/26.
 */
@Service
public class PrepareQueryCardRpcServiceImpl implements PrePareQueryCardRpcService {

    @Autowired
    private MemberModelMapper memberModelMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Autowired
    private AdvancedModelMapper advancedModelMapper;
    @Autowired
    private MpInfoService mpInfoService;
    @Autowired
    private WechatMpMapper wechatMpMapper;

    /**
     * 查一个
     *
     * @param cardQuery
     * @return
     */
    @Override
    public MemberResData queryMemberByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new MemberResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardQuery.getCardKey());
        return DataUtils.copyProperties(memberModel, MemberResData.class);
    }

    /**
     * 查所有  目前只支持mpID
     *
     * @param cardQuery
     * @return
     */
    @Override
    public MemberResDataList queryMemberList(CardQuery cardQuery) {
        String mpID = cardQuery.getMpID();
        Long groupID = cardQuery.getGroupID();
        if (StringUtils.isBlank(mpID)) {
            return new MemberResDataList().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }
        MemberModelQuery memberModelQuery = new MemberModelQuery();
        MemberModelQuery.Criteria criteria = memberModelQuery.createCriteria().andMpIDEqualTo(mpID);
        String title = cardQuery.getTitle();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }

        String cardType = cardQuery.getCardType();
        if (StringUtils.isNotBlank(cardType)) {
            criteria.andCardTypeEqualTo(cardType);
        }
        MemberResDataList memberResData = new MemberResDataList();

        Integer pageNO = cardQuery.getPageNO();
        Integer pageSize = cardQuery.getPageSize();
        if (pageNO != null && pageSize != null) {
            int count = memberModelMapper.countByExample(memberModelQuery);
            memberModelQuery.setPageNo(pageNO);
            memberModelQuery.setPageSize(pageSize);
            memberResData.setPageNO(pageNO);
            memberResData.setPageSize(pageSize);
            memberResData.setTotleCount(count);
        }
        List<MemberResData> list = new ArrayList<>();
        List<MemberModel> memberModels = memberModelMapper.selectByExample(memberModelQuery);
        for (MemberModel memberModel : memberModels) {
            list.add(DataUtils.copyProperties(memberModel, MemberResData.class));
        }
        memberResData.setMemberResData(list);
        return memberResData;
    }


    /**
     * 查一个
     *
     * @param cardQuery
     * @return
     */
    @Override
    public CouponResData queryCouponByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new CouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);

        if (null == couponModel) {
            return new CouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_RESULT_NULL, "没有找到对应的结果");
        }

        return DataUtils.copyProperties(couponModel, CouponResData.class);
    }

    /**
     * 查多个
     * @param cardQuery
     * @return
     */
    @Override
    public CouponResDataList queryCouponList(CardQuery cardQuery) {
        String mpID = cardQuery.getMpID();
        Long groupID = cardQuery.getGroupID();
        if (StringUtils.isBlank(mpID)) {
            return new CouponResDataList().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }
        CouponModelQuery couponModelQuery = new CouponModelQuery();
        CouponModelQuery.Criteria criteria = couponModelQuery.createCriteria().andMpIDEqualTo(mpID);

        String title = cardQuery.getTitle();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }

        String cardType = cardQuery.getCardType();
        if (StringUtils.isNotBlank(cardType)) {
            criteria.andCardTypeEqualTo(cardType);
        }
        CouponResDataList couponResData = new CouponResDataList();

        Integer pageNO = cardQuery.getPageNO();
        Integer pageSize = cardQuery.getPageSize();
        if (pageNO != null && pageSize != null) {
            int count = couponModelMapper.countByExample(couponModelQuery);
            couponModelQuery.setPageNo(pageNO);
            couponResData.setPageNO(pageNO);
            couponModelQuery.setPageSize(pageSize);
            couponResData.setPageSize(pageSize);
            couponResData.setTotleCount(count);
        }

        List<CouponModel> couponModels = couponModelMapper.selectByExample(couponModelQuery);
        List<CouponResData> list = new ArrayList<>();
        for (CouponModel couponModel : couponModels) {
            list.add(DataUtils.copyProperties(couponModel, CouponResData.class));
        }


        couponResData.setCouponResDataList(list);
        return couponResData;
    }


    @Override
    public CardBaseInfoResData queryBaseInfoByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new CardBaseInfoResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        BaseInfoModel baseInfoModel = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        return DataUtils.copyProperties(baseInfoModel, CardBaseInfoResData.class);
    }

    @Override
    public CardAdvancedInfoResData queryAdvancedInfoByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new CardAdvancedInfoResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        AdvancedModel advancedModel = advancedModelMapper.selectByPrimaryKey(cardKey);
        return DataUtils.copyProperties(advancedModel, CardAdvancedInfoResData.class);
    }
}
