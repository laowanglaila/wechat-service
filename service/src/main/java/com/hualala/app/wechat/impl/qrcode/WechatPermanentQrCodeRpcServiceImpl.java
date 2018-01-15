package com.hualala.app.wechat.impl.qrcode;

import com.hualala.app.wechat.WechatPermanentQrCodeRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.mapper.WechatQrcodeMapper;
import com.hualala.app.wechat.model.WechatQrcodeModel;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/10/16.
 */
@Service
public class WechatPermanentQrCodeRpcServiceImpl implements WechatPermanentQrCodeRpcService {

    @Autowired
    private WechatQrcodeMapper wechatQrcodeMapper;

    @Override
    public DelQrCodeRes delWechatQrcodeByParams(DelQrCodeReq delQrCodeReq) {
        Map <String, Object> map = DataUtils.beanToMap( delQrCodeReq );
        Integer integer = wechatQrcodeMapper.delTableQrcoed( map );


        return ResultUtil.success(DelQrCodeRes.class);
    }

    @Override
    public UpdateQrCodeRes updateQrcode(UpdateQrCodeReq updateQrCodeReq) {
        Map <String, Object> map = DataUtils.beanToMap( updateQrCodeReq );
        List <WechatQrcodeModel> list = wechatQrcodeMapper.queryQrcode( map );
        if (list == null || list.size() <= 0) {
           throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS );
        }
        WechatQrcodeModel wechatQrcodeModel = list.get( 0 );
        //逻辑删除
        if ("2".equals( updateQrCodeReq.getActionType())){
            wechatQrcodeMapper.updateDelQrCode( wechatQrcodeModel.getItemID() );
            return ResultUtil.success(UpdateQrCodeRes.class);
        }
        String tableName = updateQrCodeReq.getTableName();
        Integer isActive = updateQrCodeReq.getIsActive();
        String param1 = wechatQrcodeModel.getParam1();
        String qrcodeName = wechatQrcodeModel.getQrcodeName();
        String[] array = param1.split( "#" );
        if (array.length > 0) {
            wechatQrcodeModel.setParam1( array[0] + "#" + tableName );
        }
        String[] array1 = qrcodeName.split("-");
        if (array1.length > 0) {
            wechatQrcodeModel.setQrcodeName(array1[0] + "-" + tableName);
        }
        wechatQrcodeModel.setIsActive( isActive );
        int row = wechatQrcodeMapper.updateQrCode( wechatQrcodeModel );
        if (row != 1) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS );
        }
        return ResultUtil.success(UpdateQrCodeRes.class);
    }
}
