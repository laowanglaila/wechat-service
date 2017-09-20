package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by renjianfei on 2017/9/19.
 * 公众号店铺分组关联服务
 */
@RpcService
public interface MpShopsRpcService {

    @RpcMethod
    BindShopsResData bindShops(BindShopsReqData bindShopsReqData);

    @Data
    class BindShopsReqData extends RequestInfo {
        @NotBlank(message = "mpID不能为空")
        @Protocol(fieldType = FieldType.STRING, order = 2, description="公众号编码")
        private String mpID;
        @NotNull(message = "groupID不能为空")
        @Protocol(fieldType = FieldType.LONG, order = 3, description="集团ID")
        private Long groupID;
        @Size(min = 1,message = "shopID列表不能为空")
        @Protocol(fieldType = FieldType.LONG, order = 4, description="店铺ID列表")
        private List<Long> shopIDs;
    }
    @Data
    class BindShopsResData extends ResultInfo{

    }

}
