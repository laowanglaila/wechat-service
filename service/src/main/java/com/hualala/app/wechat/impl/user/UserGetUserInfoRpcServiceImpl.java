package com.hualala.app.wechat.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.RedisLockHandler;
import com.hualala.app.wechat.util.RequestUtil;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by renjianfei on 2017/8/17.
 */
@Service
public class UserGetUserInfoRpcServiceImpl implements UserGetUserInfoRpcService {
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private UserModelMapper userModelMapper;
    @Autowired
    private RedisLockHandler redisLockHandler;
    private static final Long LOCKED_TIME_OUT_SECONDS = 10L;

    @Override
    public UserInfoResData getUserInfoByOpenID(UserInfoReqData userInfoReqData) {
        String mpID = RequestUtil.getMpID( userInfoReqData );
        String openID = userInfoReqData.getOpenID();
        LangTypeEnum langType = userInfoReqData.getLangType();
        if (StringUtils.isBlank( openID )) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS, "openID不能为空" );
        }
        if (langType == null) {
            langType = LangTypeEnum.zh_CN;
        }
        JSONObject wechatAPIUserInfo = baseHttpService.getWechatAPIUserInfo( openID,langType, mpID );
        UserInfoResData resultInfoBean = ResultUtil.getResultInfoBean( wechatAPIUserInfo, UserInfoResData.class );
        resultInfoBean.setWechatGroupID( wechatAPIUserInfo.getString( "groupid" ) );
        return resultInfoBean;
    }

    @Override
    public UserInfoResData findUserInfo(UserInfoReqData userInfoReqData) {
        String mpID = RequestUtil.getMpID( userInfoReqData );
        String openID = userInfoReqData.getOpenID();
        LangTypeEnum langType = userInfoReqData.getLangType();
        if (StringUtils.isBlank( openID )) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS, "openID不能为空" );
        }
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        List <UserModel> userModels = userModelMapper.selectByExample( userModelQuery );
        UserModel userModel = null;
        if (userModels == null || userModels.isEmpty()) {
            userModel = this.insertDefaultUser( mpID, openID );
        } else {
            userModel = userModels.get( 0 );
        }
        this.updateUser( userInfoReqData, mpID, openID );
        UserInfoResData userInfoResData = new UserInfoResData();
        Integer isSubscribe = userModel.getIsSubscribe();
        String userNickName = userModel.getUserNickName();
        BeanUtils.copyProperties( userModel,userInfoResData );
        userInfoResData.setSubscribe( isSubscribe );
        userInfoResData.setNickname( userNickName );
        return userInfoResData;
    }

    private void updateUser(UserInfoReqData userInfoReqData, String mpID, String openID) {
        if (!WechatMessageType.HUALALA_COM.equals( mpID )) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                //使用runnable接口作为构造参数
                UserInfoResData userInfoResData = this.getUserInfoByOpenID( userInfoReqData );
                UserModel userModel = new UserModel();
                BeanUtils.copyProperties( userInfoResData ,userModel);
                Integer isSubscribe = userInfoResData.getSubscribe();
                String userNickName = userInfoResData.getNickname();
                userModel.setIsSubscribe( isSubscribe );
                userModel.setUserNickName( userNickName );
                UserModelQuery userModelQuery1 = new UserModelQuery();
                userModelQuery1.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
                userModelMapper.updateByExampleSelective( userModel ,userModelQuery1);
            } );
        }
    }

    private UserModel insertDefaultUser(String mpID, String openID) {
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        boolean tryLock = redisLockHandler.tryLock( mpID + openID, LOCKED_TIME_OUT_SECONDS );
        if (!tryLock) {
            throw new WechatException( WechatExceptionTypeEnum.WAIT_LOCK_TIMEOUT );
        }
        UserModel userModel = null;
        try {
            int i = userModelMapper.countByExample( userModelQuery );
            if (i == 0) {
                userModel = new UserModel();
                userModel.setMpID( mpID );
                userModel.setOpenid( openID );
                userModel.setUserID( -1L );
                userModelMapper.insert( userModel );
            }
        } catch (Exception e){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_USER_SERVICE_ERROR );
        }finally {
            redisLockHandler.realseLock( mpID + openID );
        }
        return userModel;
    }


}
