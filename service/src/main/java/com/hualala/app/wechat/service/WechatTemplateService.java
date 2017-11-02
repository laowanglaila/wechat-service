package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatTemplateMapper;
import com.hualala.app.wechat.model.WechatTemplateModel;
import com.hualala.app.wechat.util.template.WechatTemplateConstants;
import com.hualala.core.app.Logger;
import com.hualala.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 微信模板
 * Created by xkia on 2017/4/10.
 */
@Service
public class WechatTemplateService {


    private Logger logger = Logger.of(WechatTemplateService.class);

    @Autowired
    WechatTemplateMapper wechatTemplateMapper;

    @Autowired
    BaseHttpService baseHttpService;


    /**
     * 获取微信模板消息
     * @return
     */
    public WechatTemplateModel getTemplate(String mpID, String modelID, long groupID, String modelType){

        if(StringUtils.isEmpty(mpID) || StringUtils.isEmpty(modelID)){
            return null;
        }

        WechatTemplateModel wechatTemplateModel = wechatTemplateMapper.queryTemplate(mpID,modelID);

        if(wechatTemplateModel != null) {
            return wechatTemplateModel;
        }
        wechatTemplateModel = new WechatTemplateModel();
        wechatTemplateModel.setModelID(modelID);
        wechatTemplateModel.setMpID(mpID);
        wechatTemplateModel.setGroupID(groupID);
        wechatTemplateModel.setTemplateType(modelType);

        return initTemplate(wechatTemplateModel);
    }


    /**
     * 初始化微信模板ID
     * @param wechatTemplateModel
     * @return
     */
    public WechatTemplateModel initTemplate(WechatTemplateModel wechatTemplateModel){

        String mpID = wechatTemplateModel.getMpID();
        String modelID = wechatTemplateModel.getModelID();

        JSONObject resultJson = baseHttpService.apiAddTemplate(getContent(modelID),mpID);
        if( !resultJson.getBoolean(WechatMessageType.IS_SUCCESS)){
            logger.error(()-> "获取微信模板ID错误：" + resultJson.getString(WechatMessageType.MESSAGE));
            return null;
        }
        String templateID = resultJson.getString("template_id");
        wechatTemplateModel.setTemplateID(templateID);
        wechatTemplateModel.setModelTitle(WechatTemplateConstants.MODELID_TITLE_MAP.get(wechatTemplateModel.getModelID()));
        wechatTemplateModel.setCreateTime(DateUtils.getCurrentDateTimeLong());
        wechatTemplateMapper.insert(wechatTemplateModel);
        return wechatTemplateModel;
    }

    /**
     * 获得模板ID JSON
     * @param modelID
     * @return
     */
    private JSONObject getContent(String modelID){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("template_id_short",modelID);
        return jsonObject;
    }
}
