package com.hualala.app.wechat.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.user.UserInfoWechatRpcService;
import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.mapper.user.UserModelMapper;
import com.hualala.app.wechat.mapper.user.UserRelationModelMapper;
import com.hualala.app.wechat.model.user.UserModel;
import com.hualala.app.wechat.model.user.UserModelQuery;
import com.hualala.app.wechat.model.user.UserRelationModel;
import com.hualala.app.wechat.model.user.UserRelationModelQuery;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.RedisLockHandler;
import com.hualala.app.wechat.util.RequestUtil;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.client.BaseRpcClient;
import com.hualala.core.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

import static com.hualala.app.wechat.common.RedisKeys.WECHAT_USER_RELATION_LOCK;
import static com.hualala.app.wechat.common.RedisKeys.WECHAT_USER_INFO_LOCK;

/**
 * Created by renjianfei on 2017/8/17.
 */
@Slf4j
@Service("getUserInfoService")
public class UserGetUserInfoRpcServiceImpl implements UserGetUserInfoRpcService {
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private BaseRpcClient baseRpcClient;
    @Autowired
    private UserModelMapper userModelMapper;
    @Autowired
    private UserRelationModelMapper userRelationModelMapper;
    @Autowired
    private RedisLockHandler redisLockHandler;
    ExecutorService executor = Executors.newCachedThreadPool();

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
        JSONObject wechatAPIUserInfo = baseHttpService.getWechatAPIUserInfo( openID, langType, mpID );
        UserInfoResData resultInfoBean = ResultUtil.getResultInfoBean( wechatAPIUserInfo, UserInfoResData.class );
        resultInfoBean.setWechatGroupID( wechatAPIUserInfo.getString( "groupid" ) );
        return resultInfoBean;
    }

    @Override
    public UserInfoResData findUserInfo(UserInfoReqData userInfoReqData) {
        String mpID = RequestUtil.getMpID( userInfoReqData );
        String openID = userInfoReqData.getOpenID();
        Long userID = userInfoReqData.getUserID();

        if (StringUtils.isBlank( openID )) {
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS, "openID不能为空" );
        }
        UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
        userRelationModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        int i = userRelationModelMapper.countByExample( userRelationModelQuery );
        UserModel userModel = new UserModel();
        userModel.setOpenid( openID );
        userModel.setMpID( mpID );
        userModel.setIsSubscribe( 0 );
        userModel.setUserID( userID );
        if (!WechatMessageType.HUALALA_COM.equals( mpID )) {
            if (i < 1) {
                this.insertUserRelationWithLock( mpID, openID, userID );
            }

            Future <UserModel> future = this.insertOrUpdateUser( userInfoReqData, userModel );
            try {
                userModel = future.get( 2L, TimeUnit.SECONDS );
            } catch (InterruptedException | ExecutionException e) {
                log.error( "获取用户数据失败", e );
            } catch (TimeoutException e) {
                log.warn( "获取用户数据超时,主线程取消等待,子线程将完成更新" );
            }
        }
        UserInfoResData userInfoResData = new UserInfoResData();
        Integer isSubscribe = userModel.getIsSubscribe();
        String userNickName = userModel.getUserNickName();
        BeanUtils.copyProperties( userModel, userInfoResData );
        userInfoResData.setSubscribe( isSubscribe );
        userInfoResData.setNickname( userNickName );
        return userInfoResData;
    }

    private Future <UserModel> insertOrUpdateUser(UserInfoReqData userInfoReqData, UserModel userModel) {
        return executor.submit( () -> {
            try {
                String mpID = userModel.getMpID();
                Long userID = userModel.getUserID();
                String openid = userModel.getOpenid();
                UserInfoResData userInfoResData = this.getUserInfoByOpenID( userInfoReqData );
                UserRelationModel userRelationModel = new UserRelationModel();
                userRelationModel.setSubscribe( userInfoResData.getSubscribe() );
                userRelationModel.setUserID( userID );
                UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
                userRelationModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openid );
                userRelationModelMapper.updateByExampleSelective( userRelationModel, userRelationModelQuery );



                Integer subscribe = userInfoResData.getSubscribe();
                if (subscribe == 1 && userID != 0) {
                    UserModelQuery userModelQuery1 = new UserModelQuery();
                    userModelQuery1.createCriteria().andUserIDEqualTo( userID );
                    UserModel userModel1 = DataUtils.copyProperties( userInfoResData, UserModel.class );
                    String userNickName = userInfoResData.getNickname();
                    userModel1.setUserNickName( userNickName );
                    userModel1.setUserID( userID );
                    userModel1.setOpenid( openid );
                    userModel1.setMpID( mpID );
                    // 发送用户信息消息到user-service
                    UserInfoWechatRpcService rpcClient = baseRpcClient.getRpcClient( UserInfoWechatRpcService.class );
                    UserInfoWechatRpcService.UserInfoWechatAddReqData userInfoWechatAddReqData = new UserInfoWechatRpcService.UserInfoWechatAddReqData();
                    UserInfoWechatRpcService.UserInfoWechat userInfoWechat1 = new UserInfoWechatRpcService.UserInfoWechat();
                    userInfoWechat1.setCity( userModel1.getCity() );
                    userInfoWechat1.setCountry( userModel1.getCountry() );
                    userInfoWechat1.setHeadImgUrl( userModel1.getHeadimgurl() );
                    userInfoWechat1.setNickName( userModel1.getNickname() );
                    userInfoWechat1.setProvince( userModel1.getProvince() );
                    userInfoWechat1.setSex( userModel1.getSex().toString() );
                    userInfoWechat1.setUnionID( userInfoResData.getUnionid() );
                    userInfoWechat1.setUserID( userModel1.getUserID() );

                    userInfoWechatAddReqData.setUserInfoWechat( userInfoWechat1 );
                    UserInfoWechatRpcService.UserInfoWechatResData userInfoWechatResData = rpcClient.userInfoWechatAdd( userInfoWechatAddReqData );
                    if (!userInfoWechatResData.success()){
                        log.error( "更新用户异常" ,JSONObject.toJSONString( userModel1 ));
                    }
                    return userModel1;
                }
            } catch (Throwable e) {
                log.debug( "更新用户异常", e );
                throw e;
            }
            return userModel;
        } );

    }

    private boolean insertUserInfoWithLock(UserModel userModel1) {
        Long userID = userModel1.getUserID();
        String openid = userModel1.getOpenid();
        String mpID = userModel1.getMpID();
        UserModelQuery userModelQuery1 = new UserModelQuery();
        userModelQuery1.createCriteria().andUserIDEqualTo( userID );
        int i;
        boolean tryLock = redisLockHandler.tryLock( WECHAT_USER_INFO_LOCK + mpID + openid, LOCKED_TIME_OUT_SECONDS );
        if (!tryLock) {
            throw new WechatException( WechatExceptionTypeEnum.WAIT_LOCK_TIMEOUT, "插入用户基本信息表失败" );
        }
        boolean ifEnableInsert;
        try {
            i = userModelMapper.countByExample( userModelQuery1 );
            ifEnableInsert = i == 0;
            if (ifEnableInsert) {
                userModelMapper.insertSelective( userModel1 );
            }
        } finally {
            redisLockHandler.realseLock( WECHAT_USER_INFO_LOCK + mpID + openid );
        }
        return ifEnableInsert;
    }

    private void insertUserRelationWithLock(String mpID, String openID, Long userID) {
        UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
        userRelationModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria().andUserIDEqualTo( userID );
        boolean tryLock = redisLockHandler.tryLock( WECHAT_USER_RELATION_LOCK + mpID + openID, LOCKED_TIME_OUT_SECONDS );
        if (!tryLock) {
            throw new WechatException( WechatExceptionTypeEnum.WAIT_LOCK_TIMEOUT, "插入用户关系表失败" );
        }
        if (userID == null) {
            userID = 0L;
        }
        try {
            int i = userRelationModelMapper.countByExample( userRelationModelQuery );
            if (i == 0) {
                UserRelationModel userRelationModel = new UserRelationModel();
                userRelationModel.setMpID( mpID );
                userRelationModel.setOpenid( openID );
                userRelationModel.setUserID( userID );
                userRelationModelMapper.insertSelective( userRelationModel );
            }

        } catch (Exception e) {
            log.error( WechatExceptionTypeEnum.WECHAT_USER_SERVICE_ERROR.getMessage(), e );
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_USER_SERVICE_ERROR );
        } finally {
            redisLockHandler.realseLock( WECHAT_USER_RELATION_LOCK + mpID + openID );
        }
    }


}
