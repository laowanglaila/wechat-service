package com.hualala.app.wechat.util;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by renjianfei on 2017/4/10.
 */
public class WechatNameConverterUtil {



    /**
     * Java field to data base column-key
     */

    public static String getDBKey(String name){
        StringBuffer buffer = new StringBuffer();
        char[] array = name.toCharArray();
        List<Integer> insertIndexes = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            Character c = array[i];
            if(i != 0 && Character.isUpperCase(c)){
                insertIndexes.add(i);
            }
        }
        if(insertIndexes.size() > 0){
            int flag = 0;
            for(int j=0;j<insertIndexes.size();j++){
                String word = toLowercase4FirstLetter(name.substring(flag, insertIndexes.get(j)));
                buffer.append(word).append("_");
                flag = insertIndexes.get(j);
            }
            String last = toLowercase4FirstLetter(name.substring(flag));
            buffer.append(last);
            return buffer.toString();
        } else {
           return name;
        }
    }

    private static String toLowercase4FirstLetter(String word){
        if(word != null && word.length() > 0){
            String firstLetter = word.substring(0,1);
            String others = word.substring(1);
            return firstLetter.toLowerCase() + others;
        }else{
            return "";
        }
    }

    /*
     * data base column-key to java field
     */
    public static String getJavaKey(String key) {
        String[] words = key.split("_");
        String result = toUppercase4FirstLetter(words);
        return result;
    }

    private static String toUppercase4FirstLetter(String... words){
        StringBuffer buffer = new StringBuffer();
        if(words != null && words.length > 0){
            for(int i=0;i<words.length;i++){
                String word = words[i];
                String firstLetter = word.substring(0, 1);
                String others = word.substring(1);
                String upperLetter = null;
                if(i != 0){
                    upperLetter = firstLetter.toUpperCase();
                } else {
                    upperLetter = firstLetter;
                }
                buffer.append(upperLetter).append(others);
            }
            return buffer.toString();
        }
        return "";
    }

    /**
     * 将对象装转换成map
     * @param request
     * @return
     */
    public static Map<String, Object> toMap(Object request) {
        Map<String, Object> params = new HashMap<>();
        if (request == null) {
            return params;
        }
        Field fields[] = request.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object obj = field.get(request);
                params.put(field.getName(), obj);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return params;
    }

    /**
     * 将map中的java风格key全部替换成“_”风格
     */
    public static Map<String,Object> convertToDBStyle(Map<String,Object> map){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        HashMap<String, Object> params = new HashMap<>();
        for (Map.Entry<String, Object> entry : entries){
            Object result = null;
            Object value = entry.getValue();
            if (value instanceof Map){
                Map<String,Object> mapValue = (Map<String,Object>) value;
                result = convertToDBStyle(mapValue);
            }
            if (value instanceof List){
                List vs = (List) value;
                List<Object> list = new ArrayList<>();
                for (Object v : vs){
                    if (v instanceof Map){
                        Map<String,Object> mapValue = (Map<String,Object>) v;
                        Map<String, Object> m = convertToDBStyle(mapValue);
                        list.add(m);
                    }
                }
                if (list.size() > 0){
                    result = list;
                }else {
                    result = vs;
                }
            }
            if (null == result){
                result = value;
            }
            String dbKey = WechatNameConverterUtil.getDBKey(entry.getKey());
            params.put(dbKey,result);
        }
        return params;
    }

    /**
     * 将map中的“_”风格key全部替换成java风格
     */
    public static Map<String,Object> convertToJavaStyle(Map<String,Object> map){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        HashMap<String, Object> params = new HashMap<>();
        for (Map.Entry<String, Object> entry : entries){
            Object result = null;
            String javaKey = WechatNameConverterUtil.getJavaKey(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Map){
                Map<String,Object> mapValue = (Map<String,Object>) value;
                result = convertToJavaStyle(mapValue);
            }
            if (value instanceof List){
                List vs = (List) value;
                List<Object> list = new ArrayList<>();
                for (Object v : vs){
                    if (v instanceof Map){
                        Map<String,Object> mapValue = (Map<String,Object>) v;
                        Map<String, Object> m = convertToJavaStyle(mapValue);
                        list.add(m);
                    }
                }
                if (list.size() > 0){
                    result = list;
                }else {
                    result = vs;
                }
            }
            if (null == result){
                result = value;
            }
            params.put(javaKey,result);
        }
        return params;
    }
}
