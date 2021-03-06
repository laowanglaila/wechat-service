package com.hualala.app.wechat.impl.card;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.CardSignRpcService;
import com.hualala.app.wechat.grpc.CardSignRpcData;
import com.hualala.app.wechat.grpc.CardSignRpcServiceGrpc;
import com.hualala.core.grpc.GrpcClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/6/27.
 */
public class CardSignRpcServiceTest extends BaseRpcTest {

    @Autowired
    @Qualifier("com.hualala.app.wechat")
    private GrpcClient grpcClient;

    @Override
    public void test() {
        CardSignRpcService rpcClient = super.baseRpcClient.getRpcClient(CardSignRpcService.class);
        CardSignRpcService.CardSignReqData cardSignReqData = new CardSignRpcService.CardSignReqData();
        cardSignReqData.setHualalaCardID(1L);
        cardSignReqData.setGroupID(5L);
        cardSignReqData.setMpID("hualala_com");
        CardSignRpcService.CardSignResData cardSignResData = rpcClient.getSign(cardSignReqData);
        System.out.println(cardSignResData.getMessage());
        System.out.println(cardSignResData.toString());
    }
    @Test
    public void test2() {
        CardSignRpcServiceGrpc.CardSignRpcServiceBlockingStub blockingStub
                = (CardSignRpcServiceGrpc.CardSignRpcServiceBlockingStub) grpcClient.getBlockingStub(CardSignRpcServiceGrpc.class);
        CardSignRpcData.CardSignReqData cardSignReqData = CardSignRpcData.CardSignReqData.newBuilder()
                .setHualalaCardID(1155L)
                .setGroupID(1155L)
                .setMpID("dohko1155")
                .setHualalaCardCode("123")
                .build();
        CardSignRpcData.CardSignResData signResData = blockingStub.getSign(cardSignReqData);
        String signature = signResData.getSignature();

        Message signResData1 = signResData;
        Map<Descriptors.FieldDescriptor, Object> allFields = signResData1.getAllFields();
        Map<String, Object> map = new LinkedHashMap<>();
        for (Map.Entry<Descriptors.FieldDescriptor, Object> objectEntry :allFields.entrySet()){
            if (!(objectEntry.getValue() instanceof String) ){
                Descriptors.FieldDescriptor key = objectEntry.getKey();
                map.put(key.getJsonName(),String.valueOf(objectEntry.getValue()));
            }
        }
        System.out.println("---------"+signResData.toString());
//        System.out.println(signResData.getResult().getMessage());
//        System.out.println(signResData.toString());
    }
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CardSignRpcServiceGrpc.CardSignRpcServiceFutureStub futureStub
                = (CardSignRpcServiceGrpc.CardSignRpcServiceFutureStub) grpcClient.getFutureStub(CardSignRpcServiceGrpc.class);
        CardSignRpcData.CardSignReqData cardSignReqData = CardSignRpcData.CardSignReqData.newBuilder()
                .setHualalaCardID(1L)
                .setGroupID(5L)
                .setMpID("hualala_com")
                .build();
        CardSignRpcData.CardSignResData cardSignResData = futureStub.getSign(cardSignReqData).get();
        System.out.println(cardSignResData.getResult().getMessage());
        System.out.println(cardSignResData.toString());
    }
}
