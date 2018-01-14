package com.hualala.app.wechat.sdk.mp.util.http.okhttp;

import com.hualala.app.wechat.sdk.mp.bean.material.WxMpMaterialUploadResult;
import com.hualala.app.wechat.sdk.mp.util.http.InvoiceUploadExecutor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by renjianfei on 2018/1/13.
 */
@Slf4j
public class OkhttpInvoiceUploadExecutor extends InvoiceUploadExecutor<OkHttpClient, OkHttpProxyInfo> {

    public OkhttpInvoiceUploadExecutor(RequestHttp requestHttp){
        super(requestHttp);
    }

    @Override
    public WxMpMaterialUploadResult execute(String url, File file) throws WxErrorException, IOException {

        log.debug("Okhttp InvoiceUploadExecutor is running");
        //得到httpClient
        OkHttpClient client = requestHttp.getRequestHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType( MediaType.parse("multipart/form-data"))
                .addFormDataPart("pdf",
                        file.getName(),
                        RequestBody.create(MediaType.parse("application/pdf"), file))
                .build();

        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String responseContent = response.body().string();
        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return WxMpMaterialUploadResult.fromJson(responseContent);
    }
}
