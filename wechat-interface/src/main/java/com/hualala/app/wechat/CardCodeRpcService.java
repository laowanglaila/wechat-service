package com.hualala.app.wechat;

/**
 * Created by renjianfei on 2017/5/3.
 * 微信会员卡券code相关操作：
 * 导入、核销、更改、查询
 */
//@RpcService
public interface CardCodeRpcService {

    /**
     * 导入code
     */

//    card_id	需要进行导入code的卡券ID。	是
//    code	需导入微信卡券后台的自定义code，上限为100个。	是

//    exist_code	已经成功存入的code。
//    not_exist_code	没有存入的code。

    /**
     * 核销code
     */
//    card_id	否	string(32) 卡券ID。创建卡券时use_custom_code填写true时必填。非自定义Code不必填写。
//    code	是	string(20)	1231231	需核销的Code码。

//    openid	用户在该公众号内的唯一身份标识。
//    card_id	卡券ID。









/**
 * code解码
 */
//encrypt_code	是	string(128)  经过加密的Code码。
//code	解密后获取的真实Code码、


/**
 * 查询code接口
 */











}
