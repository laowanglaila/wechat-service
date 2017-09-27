package com.hualala.app.wechat.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.UserGetUserInfoRpcService;
import com.hualala.app.wechat.common.RedisKeys;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
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
import com.hualala.core.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import static com.hualala.app.wechat.common.RedisKeys.WECHAT_USER_RELATION_LOCK;
/**
 * Created by renjianfei on 2017/8/17.
 */
@Slf4j
@Service("getUserInfoService")
public class UserGetUserInfoRpcServiceImpl implements UserGetUserInfoRpcService {
    @Autowired
    private BaseHttpService baseHttpService;
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
        JSONObject wechatAPIUserInfo = baseHttpService.getWechatAPIUserInfo( openID,langType, mpID );
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
//        UserModelQuery userModelQuery = new UserModelQuery();
//        userModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
//        List <UserModel> userModels = userModelMapper.selectByExample( userModelQuery );
        UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
        userRelationModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        int i = userRelationModelMapper.countByExample( userRelationModelQuery );
        UserModel userModel = new UserModel();
        userModel.setOpenid( openID );
        userModel.setMpID( mpID );
        userModel.setIsSubscribe( 0 );
        userModel.setUserID( userID );
        if (i < 1 && !WechatMessageType.HUALALA_COM.equals( mpID )) {
            this.insertDefaultUserWithLock( mpID, openID ,userID);
        }
        if (!WechatMessageType.HUALALA_COM.equals( mpID )) {
            Future <UserModel> future = this.updateUser( userInfoReqData, userModel );
            try {
               userModel = future.get( 2L, TimeUnit.SECONDS );
            } catch (InterruptedException | ExecutionException e) {
                log.error( "获取用户数据失败",e );
            } catch (TimeoutException e) {
                log.warn( "获取用户数据超时,主线程取消等待,子线程将完成更新" );
            }
        }
        UserInfoResData userInfoResData = new UserInfoResData();
        Integer isSubscribe = userModel.getIsSubscribe();
        String userNickName = userModel.getUserNickName();
        BeanUtils.copyProperties( userModel,userInfoResData );
        userInfoResData.setSubscribe( isSubscribe );
        userInfoResData.setNickname( userNickName );
        return userInfoResData;
    }

    private Future<UserModel> updateUser(UserInfoReqData userInfoReqData, UserModel userModel ) {
        return executor.submit( () -> {
            UserInfoResData userInfoResData = this.getUserInfoByOpenID( userInfoReqData );
            Integer subscribe = userInfoResData.getSubscribe();
            if (subscribe == 1){
                BeanUtils.copyProperties( userInfoResData, userModel );
                Integer isSubscribe = userInfoResData.getSubscribe();
                String userNickName = userInfoResData.getNickname();
                userModel.setUserNickName( userNickName );
                UserModelQuery userModelQuery1 = new UserModelQuery();
                userModelQuery1.createCriteria().andMpIDEqualTo( userModel.getMpID() ).andOpenidEqualTo( userModel.getOpenid() );
                userModelMapper.updateByExampleSelective( userModel, userModelQuery1 );

                UserRelationModel userRelationModel = new UserRelationModel();
                userRelationModel.setSubscribe( userInfoResData.getSubscribe() );
                UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
                userRelationModelQuery.createCriteria().andMpIDEqualTo( userModel.getMpID() ).andOpenidEqualTo( userModel.getOpenid() );
                userRelationModelMapper.updateByExample( userRelationModel,userRelationModelQuery );
            }
            return userModel;

        } );

    }

    private void insertDefaultUserWithLock(String mpID, String openID,Long userID) {
        UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
        userRelationModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        UserModelQuery userModelQuery = new UserModelQuery();
        userModelQuery.createCriteria().andMpIDEqualTo( mpID ).andOpenidEqualTo( openID );
        boolean tryLock = redisLockHandler.tryLock( WECHAT_USER_RELATION_LOCK + mpID + openID, LOCKED_TIME_OUT_SECONDS );
        if (!tryLock) {
            throw new WechatException( WechatExceptionTypeEnum.WAIT_LOCK_TIMEOUT ,"插入用户关系表失败");
        }
        if (userID == null){
            userID = 0L;
        }
        try {
            int i = userRelationModelMapper.countByExample( userRelationModelQuery );
            if (i == 0) {
                UserRelationModel userRelationModel = new UserRelationModel();
                userRelationModel.setMpID( mpID );
                userRelationModel.setOpenid( openID );
                userRelationModel.setUserID( userID );
                userRelationModelMapper.insert( userRelationModel );
            }
            int i1 = userModelMapper.countByExample( userModelQuery );
            if (i1 == 0){
                UserModel userModel = new UserModel();
                userModel.setMpID( mpID );
                userModel.setOpenid( openID );
                userModelMapper.insertSelective( userModel );
            }
        } catch (Exception e){
            throw new WechatException( WechatExceptionTypeEnum.WECHAT_USER_SERVICE_ERROR );
        }finally {
            redisLockHandler.realseLock( WECHAT_USER_RELATION_LOCK + mpID + openID );
        }
    }


}
