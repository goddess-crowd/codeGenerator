package com.goddess.generator.util;

import com.google.common.base.CaseFormat;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/20/21 9:34 PM
 * @Copyright © 女神帮
 */
public class StringUtils {
    /**
     * 下划线转换为驼峰
     * @param name
     * @return
     */
    public static String toFormatCol(String name) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    /**
     * 首字母转大写
     * @param name
     * @return
     */
    public static String firstToUp(String name){
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
