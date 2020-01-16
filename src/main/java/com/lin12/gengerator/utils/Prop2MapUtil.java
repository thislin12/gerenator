package com.lin12.gengerator.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lin12
 * @date 2020/1/16
 */
public class Prop2MapUtil {


    public static Map<String, String>  prop2Map(Object t){
        Field[] fields = t.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<String, String>(20);
        for (Field field : fields) {
            field.setAccessible(true);
            String value = "";
            try {
                if(field.get(t) != null){
                    value = (String) field.get(t);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(field.getName(), value);
        }
        return map;
    }
}
