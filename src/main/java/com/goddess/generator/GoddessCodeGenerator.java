package com.goddess.generator;

import com.goddess.generator.db.DBInfo;
import com.goddess.generator.util.Properties;

import java.io.FileWriter;
import java.io.Writer;


/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021/4/19 下午5:27
 * @Copyright © 女神帮
 */
public class GoddessCodeGenerator {
    public static void main(String[] args) throws Exception {
        GoddessConfig generatorConfiguration = new GoddessConfig();
        DBInfo dataBaseInfo = new DBInfo(generatorConfiguration.getDriver(),generatorConfiguration.getUrl(),
                generatorConfiguration.getUserName(),generatorConfiguration.getPassword());
        generatorConfiguration.setTables(dataBaseInfo.getAllTables(generatorConfiguration.getTableNames()));
        Generator generator = new Generator();
        generator.setGeneratorConfiguration(generatorConfiguration);
        generator.execute();



//        Configuration conf = new Configuration(Configuration.VERSION_2_3_22);
//        //加载模板文件(模板的路径)
//        conf.setDirectoryForTemplateLoading(new File(generatorConfiguration.getTemplatePath()));
//        conf.setDefaultEncoding("UTF-8");
//        conf.setOutputEncoding("UTF-8");
//        conf.setEncoding(Locale.SIMPLIFIED_CHINESE,"UTF-8");
//        conf.setNumberFormat("###############");
//        conf.setBooleanFormat("true,false");
//        // 加载模板
//        Template template = conf.getTemplate("/${author}controller.java");
//        Map root = new HashMap();
//        root.put("author", generatorConfiguration.getAuthor());
//        // 定义输出
//        Writer out = new FileWriter(generatorConfiguration.getOutRootDir() + "/${author}controller.java");
//        template.process(generatorConfiguration, out);
//        System.out.println("转换成功");
//        out.flush();
//        out.close();
    }
}
