package com.hualala.app.wechat.impl.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.MsgPublishRpcService;
import com.hualala.app.wechat.WechatQRCodeRpcSerivce;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renjianfei on 2017/6/5.
 */
@Service
public class MsgPublishRpcServiceImpl implements MsgPublishRpcService {
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private MpInfoService mpInfoService;
    @Autowired
    private UserModelMapper userModelMapper;
    /**
     *  根据openID群发文本消息
     * @param textMsgReq
     * @return
     */
    @Override
    public TextMsgRes publishTextByUserList(TextMsgReq textMsgReq) {
        //判断mpID,没有则调方法获取
        String mpID = textMsgReq.getMpID();
        if (StringUtils.isBlank(mpID)) {
            Long brandID = textMsgReq.getBrandID();
            Long groupID = textMsgReq.getGroupID();
            Long shopID = textMsgReq.getShopID();
            if (null == brandID || null == groupID) {
                return new MsgPublishRpcService.TextMsgRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(groupID, brandID, shopID);
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new MsgPublishRpcService.TextMsgRes().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }
        List<String> openIds = textMsgReq.getOpenIds();
        if (openIds == null || openIds.size() == 0) {
            List<Long> userIds = textMsgReq.getUserIds();
            ArrayList<Integer> actions = new ArrayList<>();
            actions.add(0);
            actions.add(1);
            UserModelQuery userModelQuery = new UserModelQuery();
            userModelQuery.setDistinct(true);
            userModelQuery.createCriteria()
//                .andActionEqualTo(0)
                    .andActionIn(actions)
                    .andIsSubscribeEqualTo(1)
                    .andUserIDIn(userIds)
                    .andMpIDEqualTo(mpID);
            openIds = userModelMapper.selectByUserList(userModelQuery);
        }
        if (openIds == null || openIds.size()>10000 || openIds.size()<2) {
            return new MsgPublishRpcService.TextMsgRes().setResultInfo(ErrorCodes.WECHAT_MESSAGE_PUBLISH_WRONG_SIZE, "群发消息，最少2个，最多10000个");
        }
        String text = textMsgReq.getText();
        String json = JSONArray.toJSONString(openIds);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
        String params = "{" +
                            "\"touser\":"+json+"," +
                            "\"msgtype\": \"text\"," +
                            "\"text\": { \"content\": \""+text+"\"}" +
                         "}";
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, params, mpID);
        TextMsgRes resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, TextMsgRes.class);
        return resultInfoBean;
    }
}
