package com.goddess.generator;

import com.goddess.generator.db.Table;
import com.goddess.generator.util.Properties;
import com.goddess.generator.util.StringUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/20/21 11:55 PM
 * @Copyright © 女神帮
 */
@Data
public class GoddessConfig {

    private String centerName;
    private String date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private String centerNameUp;
    private String centerNameDesc;
    private String tableRemovePrefixes;
    private String tablesStr;
    private String userName;
    private String password;
    private String driver;
    private String url;
    private String author;
    private String email;
    private String copyright;
    private String templatePath;
    private String outRootDir;
    @Getter @Setter
    private Table thisTable;
    private List<String> tableNames = null;
    @Getter
    private List<String> templateTables = null;
    @Getter
    private List<String> templateCopyFiles = null;
    private List<Table> tables =null;

    public GoddessConfig(){
        this.outRootDir = Properties.getProperty("outRootDir");

        assert outRootDir==null:"outRootDir不能为空";

        this.templatePath = System.getProperty("user.dir")+"/src/main/resources/"+Properties.getProperty("templatePath","goddess-templates");

        this.centerName = Properties.getProperty("centerName");
        assert centerName==null:"centerName不能为空";
        this.centerNameUp = StringUtils.firstToUp(this.centerName);

        this.centerNameDesc = Properties.getProperty("centerNameDesc");

        this.tableRemovePrefixes = Properties.getProperty("tableRemovePrefixes",this.centerName+"_");

        this.tablesStr = Properties.getProperty("tables");
        List<String> tables =null;
        if(!(null==tablesStr||"".equals(tablesStr))){
            tableNames = Arrays.asList(tablesStr.split(","));
        }
        this.author = Properties.getProperty("author","失败女神");
        this.email = Properties.getProperty("email","18733123202@163.com");
        this.copyright = Properties.getProperty("copyright","女神帮");

        templateTables = Properties.getPropertyList("templateTable");
        templateTables = templateTables.stream().map(str->this.templatePath+"/"+str).collect(Collectors.toList());
        templateCopyFiles = Properties.getPropertyList("templateCopyFile");
        templateCopyFiles = templateCopyFiles.stream().map(str->this.templatePath+"/"+str).collect(Collectors.toList());
        System.out.println("");
    }
    public String getUserName() {
        if(null == userName){
            this.userName = Properties.getProperty("jdbcUserName");
        }
        assert userName==null:"jdbcUserName不能为空";
        return userName;
    }

    public String getPassword() {
        if(null == password){
            this.password = Properties.getProperty("jdbcPassword");
        }
        assert password==null:"jdbcPassword不能为空";
        return password;
    }

    public String getDriver() {
        if(null == driver){
            this.driver = Properties.getProperty("jdbcDriver");
        }
        assert driver==null:"jdbcDriver不能为空";
        return driver;
    }

    public String getUrl() {
        if(null == url){
            this.url = Properties.getProperty("jdbcUrl");
        }
        assert url==null:"jdbcDriver不能为空";
        return url;
    }
}
