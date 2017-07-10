package com.hualala.app.wechat.service.card;

import com.hualala.order.key.OrderKeyGen;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/5/9.
 */
@Service
public class CreateCardKeyService {

    public Long createCardKey(Long groupID) throws ExecutionException {
        return OrderKeyGen.genOrderKey(groupID);
    }
    public Long createCardKey() throws ExecutionException {
        return OrderKeyGen.genKey();
    }

}
