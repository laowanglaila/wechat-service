//package com.hualala.app.wechat.util;
//
//import com.dld.platform.data.Record;
//import okhttp3.*;
//import okhttp3.Request.Builder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//public class OkHttpUtil {
//
//	private static final String domain = "http://dohko.api.transformer.hualala.com";
//	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
//	private static final MediaType MEDIA_TTPE_FORM = MediaType.parse("application/x-www-form-urlencoded");
//
//	public static String doGet(String url){
//
//        final Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//        return  execute(request);
//    }
//
//	public static String doGet(String url , Map<String,Object> params){
//
//		return doGet(url , params , false);
//	}
//
//	public static void putTimeStampToMap(Map<String,Object> params){
//
//		if(params == null)
//			params = new HashMap<String,Object>();
//
//		params.put("timestamp", new Date().getTime()/1000);
//	}
//
//	public static String doGet(String url, Map<String,Object> params , boolean isCheck){
//
//		putTimeStampToMap(params);
//
//		url += getParams(params , "?");
//
//		if(isCheck)
//			url += getParams(getSignMap(params), "&");
//
//		return doGet(url);
//	}
//
//	public static Map<String,Object> getSignMap(Map<String,Object> params){
//
//			Map<String,Object> signMap = new HashMap<String,Object>();
//
//			signMap.put("sign", MD5Util.createSign(params,null));
//
//			return signMap;
//
//		}
//
//	public static String doPostByRawJson(String url , String json){
//		return doByRawJson(url , json , "POST");
//	}
//
//	public static String doPostByRawJson(String url, Map<String,Object> params , String json){
//		return doPostByRawJson(url, params, json , false );
//	}
//
//	public static String doPostByRawJson(String url, Map<String,Object> params , String json , boolean isCheck){
//		return doByRawJson(url, params, json, isCheck, "POST");
//	}
//
//	public static String doPutByRawJson(String url , String json){
//		return doByRawJson(url , json , "PUT");
//	}
//
//	public static String doPutByRawJson(String url, Map<String,Object> params , String json){
//		return doPutByRawJson(url, params, json , false );
//	}
//
//	public static String doPutByRawJson(String url, Map<String,Object> params , String json , boolean isCheck){
//		return doByRawJson(url, params, json, isCheck, "PUT");
//	}
//
//
//	public static String doByRawJson(String url , String json , String method){
//
//		RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
//
//		Builder builder = new Request.Builder()
//				.url(url);
//
//		if("post".equalsIgnoreCase(method))
//			builder.post(body);
//		else if("put".equalsIgnoreCase(method))
//			builder.put(body);
//
//		Request request = builder.build();
//
//		return execute(request);
//
//	}
//
//	public static String doByRawJson(String url, Map<String,Object> params , String json , String method){
//		return doByRawJson(url, params, json , false , method);
//	}
//
//	public static String doByRawJson(String url, Map<String,Object> params , String json , boolean isCheck , String method){
//
//		putTimeStampToMap(params);
//
//		url += getParams(params , "?");
//
//		if(isCheck)
//			url += getParams(getSignMap(params), "&");
//
//		return doByRawJson(url , json , method);
//	}
//
//	public static String doDelete(String url){
//
//		Request request = new Request.Builder()
//                .url(url)
//                .delete()
//                .build();
//        return execute(request);
//
//	}
//
//	public static String doDelete(String url , Map<String,Object> params){
//		return doDelete(url, params, false);
//	}
//
//	public static String doDelete(String url, Map<String,Object> params , boolean isCheck){
//
//		putTimeStampToMap(params);
//
//		url += getParams(params , "?");
//
//		if(isCheck)
//			url += getParams(getSignMap(params), "&");
//
//		return doDelete(url);
//    }
//
//    public static String doPostByForm(String url, Map<String, Object> params){
//
//		putTimeStampToMap(params);
//
//		url += getParams(getSignMap(params), "?");
//		String paramsStr= getParams(params, "");
//		RequestBody body = RequestBody.create(MEDIA_TTPE_FORM, paramsStr);
//
//		Builder builder = new Request.Builder()
//				.url(url);
//		builder.post(body);
//		Request request = builder.build();
//
//		return execute(request);
//	}
//
//	public static String execute(Request request){
//
//       try {
//
//            OkHttpClient.Builder httpBuilder=new OkHttpClient.Builder();
//    		OkHttpClient client=httpBuilder.readTimeout(20, TimeUnit.SECONDS)
//    				.connectTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS) //设置超时
//    				.build();
//
//            Call call = client.newCall(request);
//
//            Response response = call.execute();
//
//            return response.body().string();
//
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//       return null;
//    }
//
//	public static String getParams(Map<String,Object> params , String symbol){
//
//		StringBuffer param = new StringBuffer();
//        int i = 0;
//        for (String key : params.keySet()) {
//            if (i == 0)
//        		param.append(symbol);
//            else
//                param.append("&");
//            param.append(key).append("=").append(params.get(key));
//            i++;
//        }
//        return param.toString();
//	}
//
//	public static class SignatureHelper{
//
//		private SignatureHelper() {}
//
//	    private static Logger LOGGER = LoggerFactory.getLogger(SignatureHelper.class);
//
//	    private static String CHART_SET_UTF8 = "UTF-8";
//
//	    public static String byte2hex(byte[] b) {
//	        StringBuilder buf = new StringBuilder();
//	        int i;
//
//	        for (int offset = 0; offset < b.length; offset++) {
//	            i = b[offset];
//	            if (i < 0)
//	                i += 256;
//	            if (i < 16)
//	                buf.append("0");
//	            buf.append(Integer.toHexString(i));
//	        }
//
//	        return buf.toString();
//	    }
//
//	    public static String concatParams(Map<String, String> paramMap, String skipParam){
//	        Object[] keyArray = paramMap.keySet().toArray();
//	        Arrays.sort(keyArray);
//	        String result = "";
//
//	        try {
//	            for (Object key : keyArray) {
//	                if (key.toString().equals(skipParam))
//	                    continue;
//
//	                result += ("&" + URLEncoder.encode(key.toString(), CHART_SET_UTF8) + "="
//	                        + URLEncoder.encode(paramMap.get(key), CHART_SET_UTF8));
//	            }
//	        } catch (UnsupportedEncodingException e) {
//	            LOGGER.error("不支持此字符转换", e);
//	        }
//
//	        return result.replaceFirst("&", "");
//	    }
//
//	    public static String concatParamsWithoutLinkSymbol(Map<String, Object> paramMap, String skipParam) {
//	        Object[] keyArray = paramMap.keySet().toArray();
//	        Arrays.sort(keyArray);
//	        String result = "";
//
//	        try {
//	            for (Object key : keyArray) {
//	                if (key.toString().equals(skipParam) || paramMap.get(key).equals("") || paramMap.get(key) == null)
//	                    continue;
//
//	                result += URLEncoder.encode(key.toString(), CHART_SET_UTF8)
//	                        + URLEncoder.encode(paramMap.get(key).toString(), CHART_SET_UTF8);
//	            }
//	        } catch (UnsupportedEncodingException e) {
//	            LOGGER.error("不支持此字符转换", e);
//	        }
//
//	        return result;
//	    }
//
//	}
//
//	public static class MD5Util {
//
//		private static final String SIGN_KEY = "GHTSKey123!";
//
//		private MD5Util() {
//		}
//
//		private static String byteArrayToHexString(byte b[]) {
//			StringBuffer resultSb = new StringBuffer();
//			for (int i = 0; i < b.length; i++)
//				resultSb.append(byteToHexString(b[i]));
//
//			return resultSb.toString();
//		}
//
//		private static String byteToHexString(byte b) {
//			int n = b;
//			if (n < 0)
//				n += 256;
//			int d1 = n / 16;
//			int d2 = n % 16;
//			return hexDigits[d1] + hexDigits[d2];
//		}
//
//		private static String MD5Encode(String origin) {
//			String resultString = null;
//			try {
//				resultString = new String(origin);
//				MessageDigest md = MessageDigest.getInstance("MD5");
//
//				resultString = byteArrayToHexString(md.digest(resultString
//						.getBytes()));
//
//			} catch (Exception exception) {
//			}
//			return resultString;
//		}
//
//		private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
//				"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
//
//		public static String createSign(Map<String, Object> paramMap, String skipParam) {
//			String plainText = SignatureHelper.concatParamsWithoutLinkSymbol(paramMap, skipParam);
//			return MD5Util.MD5Encode(plainText + SIGN_KEY);
//		}
//
//		public static boolean checkSign(String signTest, Map<String, Object> paramMap, String skipParam) {
//			String sign = createSign(paramMap, skipParam);
//			return sign.equals(signTest);
//		}
//	}
//
//
//
//	public static void getMeiTuanFoods(){
//
//		//获取美团的菜品
//
//		String result = doGet(domain+"/meituan/foods", Record.Builder.create()
//				.put("groupID","33")
//				.put("shopID","75801")
//				.put("offset","1")
//				.put("limit","100")
//				.toMap(),true);
//		System.out.println(result);
//
//	}
//
//	public static void batchInsertFoods(){
//
//		String json = "{\"data\": [{\"categoryID\": \"1\", \"categorySequence\": \"0\", \"categoryName\": \"第一个分类\", \"groupID\": 33, \"shopID\": 75801, \"foodID\": \"2\", \"foodName\": \"第二个菜\", \"price\": 0, \"description\": \"特色菜品\",\"pictureInfo\": \"group1/M00/00/00/wKgCNk79Otv-FbWNAACAAFITJHA253.jpg\", \"isNew\": 0,\"isFeatured\": 0, \"isGum\": 0, \"isSpicy\": 0, \"onShelf\": \"1\", \"boxNum\": 1, \"boxPrice\": 0, \"minOrderCount\": 1, \"squence\": 0, \"isSoldOut\": 0, \"specialPrice\": 10, \"skus\": [ {\"availableTimes\": { \"friday\": \"\",\"monday\": \"\",\"saturday\": \"\",\"sunday\": \"\",\"thursday\": \"\",\"tuesday\": \"\",\"wednesday\": \"\"},\"price\": \"10.0\",\"skuID\": \"1\", \"skuName\": \"skuName\", \"stock\": \"*\", \"upc\": \"\", \"packingFee\": \"\",\"onShelf\": \"\" }],\"unit\": \"份\"}]}";
//
//		String result = doPostByRawJson(domain+"/foods" , Record.Builder.create()
//				.put("groupID","33")
//				.put("shopID","75801")
//				.put("platforms","meituan")
//				.toMap(),json,true);
//
//		System.out.println(result);
//
//	}
//
//	public static void batchUpdateFoods(){
//
//		String json = "{\"data\": [{\"categoryID\": \"1\", \"categorySequence\": \"0\", \"categoryName\": \"第二个分类\", \"groupID\": 33, \"shopID\": 75801, \"foodID\": \"1\", \"foodName\": \"第一个菜\", \"price\": 0, \"description\": \"特色菜品\",\"pictureInfo\": \"group1/M00/00/00/wKgCNk79Otv-FbWNAACAAFITJHA253.jpg\", \"isNew\": 0,\"isFeatured\": 0, \"isGum\": 0, \"isSpicy\": 0, \"onShelf\": \"1\", \"boxNum\": 1, \"boxPrice\": 0, \"minOrderCount\": 1, \"squence\": 0, \"isSoldOut\": 0, \"specialPrice\": 10, \"skus\": [ {\"availableTimes\": { \"friday\": \"\",\"monday\": \"\",\"saturday\": \"\",\"sunday\": \"\",\"thursday\": \"\",\"tuesday\": \"\",\"wednesday\": \"\"},\"price\": \"10.0\",\"skuID\": \"1\", \"skuName\": \"skuName\", \"stock\": \"0\", \"upc\": \"\", \"packingFee\": \"\",\"onShelf\": \"\" }],\"unit\": \"份\"}]}";
//
//		String result = doPutByRawJson(domain+"/foods" , Record.Builder.create()
//				.put("groupID","33")
//				.put("shopID","75801")
//				.put("platforms","meituan")
//				.toMap(),json,true);
//
//		System.out.println(result);
//
//	}
//
//	public static void batchDeleteMeiTuanFoods(){
//
//		String result = doDelete(domain+"/meituan/foods/delete", Record.Builder.create()
//				.put("groupID","33")
//				.put("shopID","75801")
//				.put("platformFoodIDs","164092995,164087957")
//				.toMap(),true);
//
//		System.out.println(result);
//
//	}
//
//	public static void batchDeleteHalalaFoods(){
//
//		String result = doDelete(domain+"/foods/delete", Record.Builder.create()
//				.put("groupID","33")
//				.put("shopID","75801")
//				.put("platforms","meituan")
//				.put("foodIDs","123456")
//				.toMap(),true);
//
//		System.out.println(result);
//
//	}
//
//	public static void queryMTTUANGOUVoucher(){
//
//		String url = "http://dohko.api.transformer.hualala.com/meituan/coupon/{groupID}/{shopID}/{couponCode}/prepare";
//
//		url = url.replace("{groupID}", "33")
//				.replace("{shopID}", "75811")
//				.replace("{couponCode}", "115120163259")
//				.replace("{count}", "99");
//
//		String queryResult = OkHttpUtil.doPostByForm(url,new HashMap<String,Object>());
//
//		System.out.println(queryResult);
//
//	}
//
//	public static void verifyMTTUANGOUVoucher(){
//
//		String url = "http://dohko.api.transformer.hualala.com/meituan/coupon/{groupID}/{shopID}/{couponCode}/{count}/consume";
//
//		url = url.replace("{groupID}", "33")
//				.replace("{shopID}", "75811")
//				.replace("{couponCode}", "115120163259")
//				.replace("{count}", "2");
//
//		String verifyResult = OkHttpUtil.doPostByForm(url,new HashMap<String,Object>());
//
//		System.out.println(verifyResult);
//
//	}
//
//	public static void queryMTMAIDANVoucher(){
//
//		String url = "http://dohko.api.transformer.hualala.com/shanhui/order/query";
//
//
//
//		String queryResult = OkHttpUtil.doPostByForm(url,Record.Builder.create()
//				.put("platforms","meituan")
//				.put("orderID","80044264129")
//				.put("groupID","3")
//				.put("shopID","75811")
//				.toMap());
//
//		System.out.println(queryResult);
//
//	}
//
//	public static void verifyMTMAIDANVoucher(){
//
//		String url = "http://dohko.api.transformer.hualala.com/shanhui/order/action";
//
//
//
//		String verifyResult = OkHttpUtil.doPostByForm(url,Record.Builder.create()
//				.put("platforms","meituan")
//				.put("orderID","80044264129")
//				.put("orderKey","123456")
//				.put("actionType","1")
//				.put("groupID","3")
//				.put("shopID","75811")
//				.toMap());
//
//		System.out.println(verifyResult);
//
//	}
//
//	public static void main(String[] args) {
//
//		//getMeiTuanFoods();
//
//		//batchInsertFoods();
//
//		//batchUpdateFoods();
//
//		//queryMTTUANGOUVoucher();
//
//		//verifyMTTUANGOUVoucher();
//
//		//queryMTMAIDANVoucher();
//
//		//verifyMTMAIDANVoucher();
//
//		/*String result = doPostByRawJson("http://dohko.api.supplychain.hualala.com/basic/supplier/querySupplierList",
//				"{\"groupID\":\"5\",\"isActive\":\"1\",\"pageSize\":\"-1\"}");
//
//		System.out.println(result);*/
//
//		String result = doPostByRawJson("http://dohko.api.supplychain.hualala.com/basic/goods/queryGoodsList",
//				"{\"groupID\":\"5\",\"categoryLevel\":\"3\",\"pageSize\":\"-1\"}");
//
//		System.out.println(result);
//
//	}
//
//}
