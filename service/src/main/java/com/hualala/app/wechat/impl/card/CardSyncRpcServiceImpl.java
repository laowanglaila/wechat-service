package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardSyncRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/5/4.
 */
@Service
public class CardSyncRpcServiceImpl implements CardSyncRpcService {


    @Autowired
    private BaseHttpService baseHttpService;

    @Override
    public CardListResData getCardList(CardListReqData cardListReqData) {
        String mpID = cardListReqData.getMpID();
        if (StringUtils.isBlank(mpID)){
            return new CardListResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY,"公众号mpID不允许为空");
        }
        int count = cardListReqData.getCount();
        int offset = cardListReqData.getOffset();
        List<String> statusList = cardListReqData.getStatusList();

        Map<String, Object> params = new HashMap<>();
        params.put("offset",offset);
        params.put("count",count);
        params.put("status_list",statusList);
        JSONObject jsonObject = baseHttpService.getBatchCardID(params, mpID);

        return ResultUtil.getResultInfoBean(jsonObject,CardListResData.class);
    }
}
