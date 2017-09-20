package com.hualala.app.wechat.impl.mp;

import com.hualala.app.wechat.MpShopsRpcService;
import com.hualala.app.wechat.mapper.mp.MpShopsMapper;
import com.hualala.app.wechat.model.mp.MpShopsModel;
import com.hualala.app.wechat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by renjianfei on 2017/9/20.
 */
@Service
public class MpShopsRpcServiceImpl implements MpShopsRpcService {
    @Autowired
    private MpShopsMapper mpShopsMapper;
    @Override
    public BindShopsResData bindShops(BindShopsReqData bindShopsReqData) {
        Long groupID = bindShopsReqData.getGroupID();
        String mpID = bindShopsReqData.getMpID();
        List <Long> shopIDs = bindShopsReqData.getShopIDs();
        MpShopsModel mpShopsModel = new MpShopsModel();
        mpShopsModel.setGroupID( groupID );
        mpShopsModel.setMpID( mpID );
        //TODO 判断公众号类型和状态
        int count = mpShopsMapper.count( mpShopsModel );
        if (count > 0){
            mpShopsMapper.delete( mpShopsModel );
        }
        for (Long shopID : shopIDs){
            mpShopsModel.setShopID( shopID );
            mpShopsMapper.insert( mpShopsModel );
        }
        return ResultUtil.success( BindShopsResData.class );
    }
}
