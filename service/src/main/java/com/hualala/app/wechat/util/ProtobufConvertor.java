package com.hualala.app.wechat.util;

import com.google.gson.Gson;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;

import java.util.*;

/**
 * Created by renjianfei on 2017/8/15.
 */
public class ProtobufConvertor {
    /**
     * 将protobuf的message对象转为JsonString，所有Number类型的值最终都换成String
     * @param message
     * @return
     */
    public static String printToString(Message message){
        Map<String, Object> map = copyToMap(message);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

    /**
     * 将protocolBuf的message对象转为map，所有类型的值最终都换成String
     * @param message
     * @return
     */
    public static Map<String,Object> copyToMap(Message message){
        Map<Descriptors.FieldDescriptor, Object> allFields = message.getAllFields();
        Map<String, Object> map = new LinkedHashMap<>();
        for (Map.Entry<Descriptors.FieldDescriptor, Object> objectEntry :allFields.entrySet()){
            Object value = objectEntry.getValue();
            Descriptors.FieldDescriptor key = objectEntry.getKey();
            if (value instanceof Message || value instanceof Collection || value instanceof Number){
                Object string = numberToString(value);
                map.put(key.getJsonName(),string);
                continue;
            }
            map.put(key.getJsonName(),value);
        }
        return map;
    }

    /**
     * 判断List的泛型是否为message，如果不是转为String
     * @param objects
     * @return
     */
    private static List toList(List objects){
        List list = new ArrayList<>();
        for (Object object:objects){
            if(object instanceof Message){
                list.add(copyToMap((Message) object));
            }else {
                list.add(numberToString(object));
            }
        }
        return list;
    }

    /**
     * 将Number类型value转换成String类型
     *
     * @param o
     * @return
     */
    private static Object numberToString(Object o){
        if (o instanceof Message){
            Message o1 = (Message) o;
            return copyToMap(o1);
        }else if (o instanceof Collection){
            List o1 = (List) o;
            return toList(o1);
        }else if (o instanceof Number){
            return String.valueOf(o);
        }
        return o;
    }


}
