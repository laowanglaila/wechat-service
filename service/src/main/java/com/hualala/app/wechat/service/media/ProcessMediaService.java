package com.hualala.app.wechat.service.media;

import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.sdk.mp.api.group.WxGroupMpService;
import com.hualala.app.wechat.sdk.mp.bean.material.WxMediaImgUploadResult;
import com.hualala.app.wechat.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by renjianfei on 2017/6/15.
 */
@Slf4j
@Service
public class ProcessMediaService {

    @Autowired
    private WxGroupMpService wxGroupMpService;

    public String uploadImage(String sourceUrl,String mpID){
        String[] split = sourceUrl.split("\\.");
        String suffix = "";
        if (split.length > 1) {
            suffix = "." + split[split.length - 1];
        }
        File file = null;
        String url = null;
        try {
            file = File.createTempFile( UUID.randomUUID().toString(), suffix);
            System.out.println(file.getPath());
            FileUtil.downloadFile(sourceUrl, file.getPath(), 1000, 1000);
            WxMediaImgUploadResult wxMediaImgUploadResult = wxGroupMpService.getMaterialService( mpID ).mediaImgUpload( file );
            url = wxMediaImgUploadResult.getUrl();
        } catch (IOException e) {
            log.error(">>> update user photoImage is error",e);
        } catch (WxErrorException e){
            log.error( ">>> update user photoImage is error",e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_ERROR,e.getMessage());
        } finally{
            if (file != null) {
                file.delete();
            }
        }
        return url;
    }

}
