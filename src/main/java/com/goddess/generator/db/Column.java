package com.goddess.generator.db;

import com.goddess.generator.util.Pg2JavaType;
import com.goddess.generator.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/20/21 9:48 PM
 * @Copyright © 女神帮
 */
public class Column {
    @Getter
    private String name;
    @Getter
    private String nameUp;
    @Getter
    private String namePrefixes;
    @Getter @Setter
    private String desc;
    @Getter @Setter
    private String sqlTypeName;
    @Getter
    private String javaTypeName;
    @Getter
    private String javaTypePathName;

    public Column(String namePrefixes, String sqlTypeName, String desc) {
        this.namePrefixes = namePrefixes;
        this.name = StringUtils.toFormatCol(namePrefixes);
        this.nameUp = StringUtils.firstToUp(this.name);

        this.desc = desc;
        this.sqlTypeName = sqlTypeName;
        this.javaTypeName = Pg2JavaType.getJavaTypeByPGSqlType(sqlTypeName);
        this.javaTypePathName = Pg2JavaType.getJavaTypePathByJavaType(this.javaTypeName);

    }
}
