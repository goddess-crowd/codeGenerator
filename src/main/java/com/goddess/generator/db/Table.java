package com.goddess.generator.db;

import com.goddess.generator.util.Properties;
import com.goddess.generator.util.StringUtils;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/20/21 9:17 PM
 * @Copyright © 女神帮
 */
@NoArgsConstructor
public class Table {
    @Getter
    private String name;
    @Getter
    private String nameUp;
    @Getter
    private String namePrefixes;
    @Getter @Setter
    private String desc;
    @Getter
    private List<Column> columns = new ArrayList();
    @Getter
    private List<String> columnTypes = new ArrayList();

    public void setNamePrefixes(String namePrefixes){
        this.namePrefixes = namePrefixes;
        String prefixes = Properties.getProperty("tableRemovePrefixes","");
        if((!"".equals(prefixes))&& namePrefixes.indexOf(prefixes)==0){
            this.name = namePrefixes.substring(prefixes.length());
        }else {
            this.name = namePrefixes;
        }
        this.name = StringUtils.toFormatCol(this.name);
        this.nameUp = StringUtils.firstToUp(this.name);
    }

    public void setColumns(List<Column> columns) {
        Set<String> types = new HashSet<>();
        this.columns = columns;
        this.columns.forEach(column -> {
            types.add(column.getJavaTypePathName());
        });
        this.columnTypes = CollectionUtils.arrayToList(types.toArray());
    }
}
