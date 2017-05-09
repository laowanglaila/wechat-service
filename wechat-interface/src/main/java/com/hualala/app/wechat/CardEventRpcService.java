package com.hualala.app.wechat;

import com.hualala.core.base.RequestInfo;
import com.hualala.core.base.ResultInfo;
import com.hualala.core.rpc.FieldType;
import com.hualala.core.rpc.Protocol;
import com.hualala.core.rpc.RpcMethod;
import com.hualala.core.rpc.RpcService;
import lombok.Data;

/**
 * Created by renjianfei on 2017/5/5.
 */
@RpcService
public interface CardEventRpcService {


    /**
     * 激活会员卡接口
     * @param activateMemberCardReqData
     * @return
     */
    @RpcMethod
    ActivateMemberCardResData activateMemberCard(ActivateMemberCardReqData activateMemberCardReqData);

    @Data
    class ActivateMemberCardResData extends ResultInfo {
    }
    @Data
    class ActivateMemberCardReqData extends RequestInfo {

        //        membership_number            	        是            	string(20)            	    会员卡编号，由开发者填入，作为序列号显示在用户的卡包里。可与Code码保持等值。
        @Protocol(fieldType = FieldType.STRING, order = 2, description = "会员卡编号，由开发者填入，作为序列号显示在用户的卡包里。可与Code码保持等值")
        private String membershipNumber;
        //        code            	                    是            	string(20)            	    领取会员卡用户获得的code
        @Protocol(fieldType = FieldType.STRING, order = 3, description = "领取会员卡用户获得的code")
        private String code;
        //        card_id                               否     	        string（32）    	        卡券ID,自定义code卡券必填
        @Protocol(fieldType = FieldType.LONG, order = 4, description = "卡券唯一键")
        private Long cardKey;
        //        background_pic_url                    否   	        string（128）               商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范
        @Protocol(fieldType = FieldType.STRING, order = 5, description = "商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范")
        private String backgroundPicUrl;
        //        activate_begin_time            	    否            	unsigned int            	激活后的有效起始时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式。
        @Protocol(fieldType = FieldType.INT, order = 6, description = "激活后的有效起始时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式")
        private Integer activateBeginTime;
        //        activate_end_time            	        否            	unsigned int            	激活后的有效截至时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式。
        @Protocol(fieldType = FieldType.INT, order = 7, description = "激活后的有效截至时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式")
        private Integer activateEndTime;
        //        init_bonus            	            否            	int            	            初始积分，不填为0。
        @Protocol(fieldType = FieldType.INT, order = 8, description = "初始积分，不填为0")
        private Integer initBonus;
        //        init_bonus_record	                    否	            string(32)	                积分同步说明。
        @Protocol(fieldType = FieldType.STRING, order = 9, description = "积分同步说明")
        private String initBonusRecord;
        //        init_balance            	            否            	int            	            初始余额，不填为0。
        @Protocol(fieldType = FieldType.INT, order = 10, description = "初始余额，不填为0。")
        private Integer initBalance;
        //        init_custom_field_value1            	否            	string（12）            	创建时字段custom_field1定义类型的初始值，限制为4个汉字，12字节。
        @Protocol(fieldType = FieldType.STRING, order = 11, description = "创建时字段custom_field1定义类型的初始值，限制为4个汉字，12字节")
        private String initCustomFieldValue1;
        //        init_custom_field_value2            	否            	string（12）            	创建时字段custom_field2定义类型的初始值，限制为4个汉字，12字节。
        @Protocol(fieldType = FieldType.STRING, order = 12, description = "创建时字段custom_field2定义类型的初始值，限制为4个汉字，12字节")
        private String initCustomFieldValue2;
        //        init_custom_field_value3            	否            	string（12）            	创建时字段custom_field3定义类型的初始值，限制为4个汉字，12字节。
        @Protocol(fieldType = FieldType.STRING, order = 13, description = "创建时字段custom_field3定义类型的初始值，限制为4个汉字，12字节")
        private String initCustomFieldValue3;

    }
}
