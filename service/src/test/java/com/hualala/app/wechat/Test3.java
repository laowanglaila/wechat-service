package com.hualala.app.wechat;

import com.hualala.core.utils.DateUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by renjianfei on 2017/10/23.
 */
@SpringBootTest
public class Test3
{

    /* 搅浑序列码 ，随机产生*/
    private static final String CONFUSE_MAP_CODE = "mXUbVJxIFftzHNu1e9jGRKdY68EaBi3nDO0MW:5wAy-rZ7c";

    /* 欺骗码，随机产生，不能与CONFUSE_MAP_CODE的字符冲突*/
    private static final String CHEET_MAP_CODE = "TPgkL4qSh2ClopvsQ";

    /* 序列基 */
    private static final String CONFUSE_CHAR_BASE = "abcdefghijklmnopqrstuvwxyz1234567890-+ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /* 随机码生成器 */
    private static final Random random = new Random(System.currentTimeMillis());

    /* 欺骗数 */
    private static final BigInteger MASK_NUM = new BigInteger("9021768");

    /**
     * 将指定字符串中的字符打乱
     *
     * @param codeMap 字符串
     * @return 打乱后的字符串
     */
    public static String confuseCode(String codeMap) {
        StringBuffer bg = new StringBuffer(codeMap);
        StringBuffer ret = new StringBuffer( bg.length( ) );
        while ( bg.length() > 0 ) {
            int index = random.nextInt(bg.length());
            ret.append(bg.charAt(index));
            bg=bg.deleteCharAt( index );
        }
        return ret.toString();
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

    /**
     * 将指定的穿按照算法解密
     *
     * @param str 字符串
     * @return  解密后串，如果解密失败，返回为null
     */
    public static String decodeNumberCode(String str) {

        if (str == null || str.length() == 0)
            return null;

        BigInteger bt = new BigInteger("0");
        BigInteger mapLen = new BigInteger(CONFUSE_MAP_CODE.length() + "");
        String source = new StringBuffer(str.toString()).reverse().toString();
        for (int i = 0; i < source.length(); i++) {
            int indexOf = CONFUSE_MAP_CODE.indexOf(source.charAt(i));
			/* 按照进制还原 */
            if (indexOf >= 0) {
                bt = bt.multiply(mapLen).add(new BigInteger(indexOf + ""));
            } else if (CHEET_MAP_CODE.indexOf(source.charAt(i)) < 0)/* 碰到非法字符，解密失败 */
                return null;
        }

        bt = bt.subtract(MASK_NUM);
        if (bt.toString().length() < 1)
            return null;
		/* 讲获得字符串反转 */
        String decodeResult = new StringBuffer(bt.toString().substring(1))
                .reverse().toString();
        if (str.equals(encodeNumberCode(decodeResult)))
            return decodeResult;
        return null;
    }
    @org.junit.Test
    public void test(){
        String encode = null;
        String s = decodeNumberCode( encode );
        System.out.println(s);
    }
    @org.junit.Test
    public void test1(){
        BigDecimal bigDecimal = new BigDecimal( "0" );
        BigDecimal subtract = BigDecimal.ZERO.subtract( bigDecimal );
        BigDecimal abs = subtract.abs();
        System.out.println( abs.longValue() < 0.001);
    }
    @org.junit.Test
    public void test2(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
        String format = simpleDateFormat.format( new Date() );
        System.out.println(Long.parseLong( format ));
        long currentDateTimeLong = DateUtils.getCurrentDateTimeLong();
        System.out.println(currentDateTimeLong);
    }
    @org.junit.Test
    public void test3(){
        System.out.println( true ^ true);
    }
}
