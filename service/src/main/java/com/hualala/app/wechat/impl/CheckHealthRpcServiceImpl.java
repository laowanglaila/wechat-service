package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.CheckHealthRpcService;
import com.hualala.app.wechat.sdk.mp.common.ErrorCodes;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/5/2.
 */
@Service
public class CheckHealthRpcServiceImpl implements CheckHealthRpcService {

    @Override
    public CheckHealthResData check(CheckHealthReqData checkHealthReqData) {
        CheckHealthResData checkHealthResData = new CheckHealthResData();
        checkHealthResData.setResultInfo(ErrorCodes.WECHAT_SUCCESS_CODE,"请求成功！");

        checkHealthResData.setResponseTime(System.currentTimeMillis());

        return checkHealthResData;
    }
}
