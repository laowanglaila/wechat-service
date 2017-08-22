package com.hualala.app.wechat.util;

import com.hualala.core.base.ValueEnum;

/**
 * Created by renjianfei on 2017/8/8.
 */
public class EnumUtil {

    public static <T extends ValueEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getValue())){
                return each;
            }
        }
        return null;
    }

}
