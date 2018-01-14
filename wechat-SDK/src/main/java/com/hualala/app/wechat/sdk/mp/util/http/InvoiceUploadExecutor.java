package com.hualala.app.wechat.sdk.mp.util.http;

import com.hualala.app.wechat.sdk.mp.bean.material.WxMpMaterialUploadResult;
import com.hualala.app.wechat.sdk.mp.util.http.okhttp.OkhttpInvoiceUploadExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.File;

/**
 * Created by renjianfei on 2018/1/13.
 */
public abstract class InvoiceUploadExecutor<H, P> implements RequestExecutor<WxMpMaterialUploadResult, File> {

    protected RequestHttp<H, P> requestHttp;
    public InvoiceUploadExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    public static RequestExecutor<WxMpMaterialUploadResult, File> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
//                return new ApacheQrCodeRequestExecutor(requestHttp);
            case JODD_HTTP:
//        return new JoddQrCodeRequestExecutor(requestHttp);
            case OK_HTTP:
                return new OkhttpInvoiceUploadExecutor(requestHttp);
            default:
                //TODO 需要优化，最好抛出异常
                return null;
        }
    }

}
