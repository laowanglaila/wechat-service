/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package com.hualala.app.wechat.sdk.mp.util.json;

import com.google.gson.*;
import com.hualala.app.wechat.sdk.mp.bean.material.WxMpMaterialUploadResult;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * @author codepiano
 */
public class WxMpMaterialUploadResultAdapter implements JsonDeserializer<WxMpMaterialUploadResult> {

  @Override
  public WxMpMaterialUploadResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMaterialUploadResult uploadResult = new WxMpMaterialUploadResult();
    JsonObject uploadResultJsonObject = json.getAsJsonObject();

    if (uploadResultJsonObject.get("errcode") != null && !uploadResultJsonObject.get("errcode").isJsonNull()) {
      uploadResult.setErrCode( GsonHelper.getAsInteger(uploadResultJsonObject.get("errcode")));
    }
    if (uploadResultJsonObject.get("errmsg") != null && !uploadResultJsonObject.get("errmsg").isJsonNull()) {
      uploadResult.setErrMsg( GsonHelper.getAsString(uploadResultJsonObject.get("errmsg")));
    }

    if (uploadResultJsonObject.get("url") != null && !uploadResultJsonObject.get("url").isJsonNull()) {
      uploadResult.setUrl( GsonHelper.getAsString(uploadResultJsonObject.get("url")));
    }
    if (uploadResultJsonObject.get("media_id") != null && !uploadResultJsonObject.get("media_id").isJsonNull()) {
      uploadResult.setMediaId( GsonHelper.getAsString(uploadResultJsonObject.get("media_id")));
    } else if (uploadResultJsonObject.get("s_media_id") != null && !uploadResultJsonObject.get("s_media_id").isJsonNull()) {
      uploadResult.setMediaId( GsonHelper.getAsString(uploadResultJsonObject.get("s_media_id")));
    }
    return uploadResult;
  }

}
