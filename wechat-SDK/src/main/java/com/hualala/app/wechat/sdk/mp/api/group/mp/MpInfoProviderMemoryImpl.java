package com.hualala.app.wechat.sdk.mp.api.group.mp;

import com.hualala.app.wechat.grpc.MpInfoRpcData;
import com.hualala.app.wechat.grpc.MpInfoRpcServiceGrpc;
import com.hualala.app.wechat.sdk.mp.common.TimeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by renjianfei on 2018/1/5.
 */
@Component
@Slf4j
public class MpInfoProviderMemoryImpl implements MpInfoProvider {
    private static long EXPIRE = 1000*60*60*2;
    private Map<String,MpInfoRelation> relationMap = new HashMap <>();


    @Autowired
    private MpInfoRpcServiceGrpc.MpInfoRpcServiceFutureStub mpInfoRpcServiceFutureStub;

    public MpInfoRelation getMpInfoByAppID(String appID) {
        MpInfoRelation mpInfoRelation = relationMap.get( appID );
        String mpID;
        long now = System.currentTimeMillis();
        if (mpInfoRelation == null ||!(mpInfoRelation.getCreateTime() + EXPIRE > now)){
            MpInfoRpcData.MpInfoQueryReqData.Builder builder = MpInfoRpcData.MpInfoQueryReqData.newBuilder();
            builder.setAppID( appID );
            MpInfoRpcData.MpInfoResData mpInfoResDataList = getRelation( builder );
             mpID = mpInfoResDataList.getMpID();
            mpInfoRelation = new MpInfoRelation();
            mpInfoRelation.setAppID( appID );
            mpInfoRelation.setMpID( mpID );
            mpInfoRelation.setGroupID( mpInfoResDataList.getGroupID() );
            mpInfoRelation.setAuthorize( mpInfoResDataList.getAuthorize() );
            relationMap.put( appID,mpInfoRelation );
        }
        return mpInfoRelation;
    }

    public MpInfoRelation getMpInfoByMpID(String mpID) {

        MpInfoRelation mpInfoRelation = relationMap.get( mpID );
        String appID;
        long now = System.currentTimeMillis();
        if (mpInfoRelation == null ||!(mpInfoRelation.getCreateTime() + EXPIRE > now)){
            MpInfoRpcData.MpInfoQueryReqData.Builder builder = MpInfoRpcData.MpInfoQueryReqData.newBuilder();
            builder.setMpID( mpID );
            MpInfoRpcData.MpInfoResData mpInfoResDataList = getRelation( builder );
            mpInfoRelation = new MpInfoRelation();
            mpInfoRelation.setMpID( mpID );
            mpInfoRelation.setAppID( mpInfoResDataList.getAppID() );
            mpInfoRelation.setSecret( mpInfoResDataList.getAppSecret() );
            mpInfoRelation.setGroupID( mpInfoResDataList.getGroupID() );
            mpInfoRelation.setAuthorize( mpInfoResDataList.getAuthorize() );
            mpInfoRelation.setToken( mpInfoResDataList.getToken() );
            mpInfoRelation.setAesKey( mpInfoResDataList.getEncodingAESKey() );
            relationMap.put( mpID,mpInfoRelation );
        }
        return mpInfoRelation;
    }

    private MpInfoRpcData.MpInfoResData getRelation(MpInfoRpcData.MpInfoQueryReqData.Builder builder) {

        MpInfoRpcData.MpInfoQueryResData mpInfoQueryResData = null;
        try {
            mpInfoQueryResData = mpInfoRpcServiceFutureStub
                    .withDeadlineAfter( TimeConstants.GRPC_TIMEOUT, TimeUnit.SECONDS )
                    .queryMpInfo( builder.build() )
                    .get();
        } catch (InterruptedException e) {
            log.error( "GRPC 接口请求异常",e );
        } catch (ExecutionException e) {
            log.error( "GRPC 接口执行异常",e );
        }
        return mpInfoQueryResData.getMpInfoResDataList( 0 );
    }
}
