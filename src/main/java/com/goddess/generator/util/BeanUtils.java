package com.goddess.generator.util;

import lombok.val;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/21/21 9:24 AM
 * @Copyright © 女神帮
 */
public class BeanUtils {
    //Object转Map
    public static Map<String, Object> getObjectToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null){
                value = "";
            }
            if(isBaseType(value)){
                map.put(keyName, value);
            }else {
                map.put(keyName, BeanUtils.getObjectToMap(value));
            }
        }
        return map;
    }
    private static boolean isBaseType(Object val){
        List<Class> baseClass = new ArrayList<>();
        baseClass.add(String.class);
        baseClass.add(Boolean.class);
        baseClass.add(boolean.class);
        baseClass.add(byte.class);
        baseClass.add(Byte.class);
        baseClass.add(char.class);
        baseClass.add(Short.class);
        baseClass.add(short.class);
        baseClass.add(int.class);
        baseClass.add(Integer.class);
        baseClass.add(long.class);
        baseClass.add(Long.class);
        baseClass.add(float.class);
        baseClass.add(Float.class);
        baseClass.add(double.class);
        baseClass.add(Double.class);
        baseClass.add(BigDecimal.class);
        baseClass.add(BigInteger.class);
        for (Class clazz:baseClass) {
            if(baseClass.contains(val.getClass())){
                return true;
            }
        }
        return false;
    }
}
