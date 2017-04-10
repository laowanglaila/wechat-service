package com.hualala.app.wechat.util;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by renjianfei on 2017/4/10.
 */
public class WechatNameConverterUtil {



    /*
     * Java field to data base column-key
     */

    public static void getDBKey(String... javaFieldNames){
        if(javaFieldNames != null && javaFieldNames.length > 0){
            for(String name : javaFieldNames){
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
                    System.out.println(buffer.toString());
                } else {
                    System.out.println(name);
                }
            }
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
    public static void convertToJava(String... dbKeys) {
        if(dbKeys != null && dbKeys.length > 0){
            for(String key : dbKeys){
                String[] words = key.split("_");
                String result = toUppercase4FirstLetter(words);
                System.out.println(result);
            }
        }
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
}
