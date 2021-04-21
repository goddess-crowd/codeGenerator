package com.goddess.generator.db;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/20/21 9:11 PM
 * @Copyright © 女神帮
 */
public class DBInfo {
    private Connection conn =null;
    private String driver;
    private String url;
    private String user;
    private String password;

    public DBInfo(String driver, @NotNull String url, @NotNull String user, @NotNull String password) throws SQLException, ClassNotFoundException {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        getConn();
    }

    public Connection getConn()throws ClassNotFoundException, SQLException {
        if (conn==null){
            Class.forName(this.driver==null?"org.postgresql.Driver":this.driver);
            conn = DriverManager.getConnection(this.url,this.user, this.password);
        }
        return conn;
    }

    public List<Table> getAllTables(List<String> tableNames) throws Exception {
        DatabaseMetaData dbmt = getConn().getMetaData();
        ResultSet tableSet = dbmt.getTables(null,"%", "%",new String[]{"TABLE"});
        dbmt = getConn().getMetaData();
//        Statement st = getConn().createStatement();
        List<Table> tables = new ArrayList();
        while (tableSet.next()){
            //获取数据库中表名称
            String tableName = tableSet.getString(3);
            String tableNameDesc =tableSet.getString(5);
            //要生成的表没有这个则跳过
            if(tableNames!=null&&!tableNames.contains(tableName)){
                continue;
            }
            Table table = new Table();
            table.setNamePrefixes(tableName);
            table.setDesc(tableNameDesc==null||tableNameDesc.equals("")?"对象实体":tableNameDesc);
            //获取列信息
            ResultSet columnSet = dbmt.getColumns(null,"%", tableName,"%");
            List columns = new ArrayList();
            while(columnSet.next()){
                //获取列名称
                String columnName = columnSet.getString("COLUMN_NAME");
                //列字段类型
                String sqlTypeName = columnSet.getString("TYPE_NAME");
                //列字段描述
                String remarks = columnSet.getString("remarks");
                columns.add(new Column(columnName,sqlTypeName,remarks));
            }
            table.setColumns(columns);
            tables.add(table);
        }
        return tables;
    }
}
