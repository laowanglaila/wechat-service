package com.hualala.app.wechat.util;

import java.math.BigInteger;
import java.util.UUID;

/**
 * Created by renjianfei on 2017/8/31.
 */
public class MpIDProducer {

    /* 搅浑序列码 ，随机产生*/
    private static final String CONFUSE_MAP_CODE = "mXUbVJxIFftzHNu1e9jGRKdY68EaBi3nDO0MW:5wAy-rZ7c";

    /* 欺骗码，随机产生，不能与CONFUSE_MAP_CODE的字符冲突*/
    private static final String CHEET_MAP_CODE = "TPgkL4qSh2ClopvsQ";

    /* 欺骗数 */
    private static final BigInteger MASK_NUM = new BigInteger("9021768");


    public static String initMpID(){
            Long time = System.currentTimeMillis();
            // 时间加密算法生成字符串
            String timeSign = encodeNumberCode( time.toString() ).replace( ":", "" );
            // 避免并发重复，拼接随机值
        return timeSign + UUID.randomUUID().toString().substring( 0, 6 );
    }


    /**
     * 将指定的数字按照算法加密
     *
     * @param str 字符串
     * @return  加密后串
     */
    public static String encodeNumberCode(String str) {
        StringBuffer ret = new StringBuffer("");

		/* 模数 */
        int mod9 = new BigInteger(str).mod(new BigInteger("73")).mod(new BigInteger("9")).intValue( );
        if( mod9 == 0 )
            mod9=9;

		/* 将给定的数字末尾加模数（防止末位出现0），反转，与欺骗数相加 */
        BigInteger sourceNum = new BigInteger(new StringBuffer(str).append(mod9)
                                                                   .reverse().toString()).add(MASK_NUM);

        BigInteger divideNum = sourceNum;

        BigInteger mapLen = new BigInteger(CONFUSE_MAP_CODE.length() + "");
        BigInteger cheetLen = new BigInteger(CHEET_MAP_CODE.length() + "");

		/* 根据搅浑码的长度转换进制 */
        while (divideNum.compareTo(mapLen) >= 0) {
            BigInteger currNum = divideNum.mod(mapLen);
            char chr = CONFUSE_MAP_CODE.charAt(currNum.intValue());
            ret.append(chr);
            divideNum = divideNum.divide(mapLen);
        }
        if (divideNum.compareTo(new BigInteger("0")) >= 0)
            ret.append(CONFUSE_MAP_CODE.charAt(divideNum.intValue()));

		/* 插入欺骗码 */
        int cheetCharOffSet = sourceNum.mod(cheetLen).intValue();
        int cheetPos = sourceNum.mod(new BigInteger(ret.length() + "")).intValue();
        if (cheetPos == str.length())
            ret.append(CHEET_MAP_CODE.charAt(cheetCharOffSet));
        else
            ret.insert(cheetPos, CHEET_MAP_CODE.charAt(cheetCharOffSet));

        return ret.toString();
    }
}
