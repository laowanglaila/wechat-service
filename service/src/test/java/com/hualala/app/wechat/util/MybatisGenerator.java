package com.hualala.app.wechat.util;

import com.hualala.core.service.utils.MybatisSqlGenerator;
import org.junit.Test;

/**
 * mybatis util
 * Created by xkia on 2017/2/16.
 */
public class MybatisGenerator {
    @Test
    public void testGeneratorMybatis() {

        //new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_master", "output");
        // new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_settle_detail");
        // new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_order_payDetail");
        //new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_external_map", "output");
        // new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_shop_map", "output");

        new MybatisSqlGenerator().generatorMySql("db_shop", "tbl_shop_wechat_autoreply", "wechat",0);
        new MybatisSqlGenerator().generatorMySql("db_shop", "tbl_shop_wechat_mp", "wechat",0);
        new MybatisSqlGenerator().generatorMySql("db_shop", "tbl_shop_wechat_qrcode", "wechat",0);
        new MybatisSqlGenerator().generatorMySql("db_shop", "tbl_shop_wechat_qrcode_temp", "wechat",0);
        new MybatisSqlGenerator().generatorMySql("db_shop", "tbl_shop_wechat_template", "wechat",0);
        new MybatisSqlGenerator().generatorMySql("db_shop", "tbl_shop_wechat_user", "wechat",0);
//

        /*
        tbl_shop_wechat_autoreply
tbl_shop_wechat_mp
tbl_shop_wechat_qrcode
tbl_shop_wechat_qrcode_temp
tbl_shop_wechat_resources
tbl_shop_wechat_template
tbl_shop_wechat_user

         */
//        new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_master", "output");
//
//        new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_detail", "output");
//
//        new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_bank_config", "output");
//
//        new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_settle_map", "output");
//
//        new MybatisSqlGenerator().generatorMySql("db_pay", "tbl_pay_account_shop_map", "output");
    }

}
