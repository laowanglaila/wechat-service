package com.hualala.app.wechat.service;

import com.hualala.app.wechat.mapper.WechatMpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xkia on 2017/3/21.
 */
@Service
public class MpInfoService {

    @Autowired
    private WechatMpMapper wechatMpMapper;

    /**
     * query mpInfo by mpID or appID
     *
     * @param param
     */
    public Map<String, Object> queryMpInfo(Map<String, Object> param) {

        List<Map<String, Object>> list = wechatMpMapper.queryByParams(param);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }


    /**
     * 获取具有api权限的公众号
     *
     * @param groupID
     * @param brandID
     * @param shopID
     * @return
     */
    public String queryMpIDAuth(long groupID, long brandID, long shopID) {
        String mpID = wechatMpMapper.queryMpIDAuth(groupID, brandID, shopID);

        if (StringUtils.isEmpty(mpID)) {
            return null;
        }
        return mpID;
    }

}
