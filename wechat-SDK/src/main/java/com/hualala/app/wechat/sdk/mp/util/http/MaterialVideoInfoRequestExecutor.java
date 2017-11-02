package com.hualala.app.wechat.sdk.mp.util.http;


import com.hualala.app.wechat.sdk.mp.bean.material.WxMpMaterialVideoInfoResult;
import com.hualala.app.wechat.sdk.mp.util.http.apache.ApacheMaterialVideoInfoRequestExecutor;
import com.hualala.app.wechat.sdk.mp.util.http.okhttp.OkhttpMaterialVideoInfoRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;


public abstract class MaterialVideoInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialVideoInfoResult, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialVideoInfoRequestExecutor(RequestHttp requestHttp) {
          this.requestHttp = requestHttp;
        }

        public static RequestExecutor<WxMpMaterialVideoInfoResult, String> create(RequestHttp requestHttp) {
          switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
              return new ApacheMaterialVideoInfoRequestExecutor(requestHttp);
            case JODD_HTTP:
//              return new JoddMaterialVideoInfoRequestExecutor(requestHttp);
            case OK_HTTP:
              return new OkhttpMaterialVideoInfoRequestExecutor(requestHttp);
            default:
              return null;
          }
        }

      }
