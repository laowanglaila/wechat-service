package com.hualala.app.wechat.model.card;

import com.hualala.core.base.BaseItem;
import lombok.Data;

import java.io.Serializable;
@Data
public class AdvancedModel extends BaseItem implements Serializable {
    /**
     * 会员端产生的唯一主键
     */
    private Long cardKey;

    /**
     * 封面摘要结构体  封面摘要简介/封面图片列表，仅支持填入一个封面图片链接，上传图片接口上传获取图片获得链接，填写非CDN链接会报错，并在此填入。建议图片尺寸像素850*350
     */
    private String abstractInfo;

    /**
     * 商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选
     */
    private String businessService;

    /**
     * 图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表  图文描述/图片链接，必须调用上传图片接口上传图片获得链接，并在此填入，否则报错。
     */
    private String textImage;

    /**
     * 使用时段限制
     */
    private String timeLimit;

    /**
     * 使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享
     */
    private String useCodition;

}