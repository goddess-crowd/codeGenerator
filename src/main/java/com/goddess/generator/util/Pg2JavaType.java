package com.goddess.generator.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/20/21 9:55 PM
 * @Copyright © 女神帮
 */
public class Pg2JavaType {
    private static Map<String, String> javaTypeForPGSqlType =new HashMap<>();
    private static Map<String, String> javaTypePath=new HashMap<>();
    static {
        javaTypePath.put("Integer","java.lang.Integer");
        javaTypePath.put("Short","java.lang.Short");
        javaTypePath.put("Long","java.lang.Long");
        javaTypePath.put("Float","java.lang.Float");
        javaTypePath.put("Double","java.lang.Double");
        javaTypePath.put("BigDecimal","java.math.BigDecimal");
        javaTypePath.put("String","java.lang.String");
        javaTypePath.put("LocalDate","java.time.LocalDate");
        javaTypePath.put("LocalTime","java.time.LocalTime");
        javaTypePath.put("LocalDateTime","java.time.LocalDateTime");
        javaTypePath.put("Boolean","java.lang.Boolean");
    }
    static {
        javaTypeForPGSqlType.put("int", "Integer");
        javaTypeForPGSqlType.put("int2", "Short");
        javaTypeForPGSqlType.put("int4", "Integer");
        javaTypeForPGSqlType.put("int8", "Long");

        javaTypeForPGSqlType.put("float", "Float");
        javaTypeForPGSqlType.put("float4", "Double");
        javaTypeForPGSqlType.put("float8", "Double");

        javaTypeForPGSqlType.put("numeric", "BigDecimal");

        javaTypeForPGSqlType.put("bpchar", "String");
        javaTypeForPGSqlType.put("varchar", "String");
        javaTypeForPGSqlType.put("text", "String");

        javaTypeForPGSqlType.put("date", "LocalDate");
        javaTypeForPGSqlType.put("time", "LocalTime");
        javaTypeForPGSqlType.put("timestamp", "LocalDateTime");

        javaTypeForPGSqlType.put("bool", "Boolean");

        javaTypeForPGSqlType.put("json", "String");

    }

    public static String getJavaTypeByPGSqlType(String pgSqlType) {
        return javaTypeForPGSqlType.get(pgSqlType);
    }
    public static String getJavaTypePathByJavaType(String javaType) {
        return javaTypePath.get(javaType);
    }
}
