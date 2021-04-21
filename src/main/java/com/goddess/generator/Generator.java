package com.goddess.generator;

import com.goddess.generator.util.BeanUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Setter;
import org.apache.commons.text.StringSubstitutor;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 4/21/21 1:19 AM
 * @Copyright © 女神帮
 */
public class Generator {
    @Setter
    GoddessConfig generatorConfiguration;

    public void execute(){
        file(generatorConfiguration.getTemplatePath());
    }
    private List<String> file(String path){

        List<String> files = new ArrayList<>();
        //获取其file对象
        File file = new File(path);
        //遍历path下的文件和目录，放在File数组中
        File[] fs = file.listFiles();
        //遍历File[]数组
        for(File f:fs){
            String outName = f.getPath().replace(generatorConfiguration.getTemplatePath(),generatorConfiguration.getOutRootDir());
            String tempName = f.getName();
            String tempPath = f.getPath();
            if(f.isDirectory()) {
                saveFile(tempPath,tempName,outName,false);
                file(f.getPath());
            }else {
                saveFile(tempPath,tempName,outName,true);
            }

        }
        return files;
    }
    private void creatFile(String tempPath,String tempName,String newFileName,boolean isFile){
        if(!isFile){
            File f = new File(newFileName);
            if (!f.exists()) {
                f.mkdirs();
            }
        }else {
            saveTableFile(tempPath,tempName,newFileName);
        }
    }
    private void saveFile(String tempPath,String tempName,String outName,boolean isFile){
        Map<String, Object> map = BeanUtils.getObjectToMap(generatorConfiguration);
        StringSubstitutor sub = new StringSubstitutor(map);
        String newFileName = sub.replace(outName);

        if(generatorConfiguration.getTemplateCopyFiles().contains(tempPath)){
            copyFileUsingJava7Files(tempPath,newFileName);
            return;
        }

        if(generatorConfiguration.getTemplateTables().contains(tempPath)){
            generatorConfiguration.getTables().forEach(table -> {
                generatorConfiguration.setThisTable(table);
                String newFileName2 =null;
                try {
                    Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
                    cfg.setDefaultEncoding("UTF-8");
                    Template template = new Template("", outName, cfg);
                    Writer out = new StringWriter();
                    template.process(BeanUtils.getObjectToMap(generatorConfiguration), out);
                    newFileName2 =out.toString();
                } catch (IOException | TemplateException e) {
                    e.printStackTrace();
                }
                creatFile(tempPath,tempName,newFileName2,isFile);
            });
        }else {
            creatFile(tempPath,tempName,newFileName,isFile);
        }
    }
    private  void copyFileUsingJava7Files(String tempPath,String newFileName) {
        File source = new File(tempPath);
        File dest = new File(newFileName);
        if (!dest.exists()) {
            try {
                Files.copy(source.toPath(), dest.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveTableFile(String tempPath,String tempName,String newFileName){
        try {
            Template template = getTemplate(tempPath,tempName);
            Writer out = new FileWriter(newFileName);
            template.process(generatorConfiguration, out);
            generatorConfiguration.setThisTable(null);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private Template getTemplate(String tempPath,String tempName) {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_22);
        try {
            conf.setDirectoryForTemplateLoading(new File(new File(tempPath).getParent()));
            conf.setDefaultEncoding("UTF-8");
            conf.setOutputEncoding("UTF-8");
            conf.setEncoding(Locale.SIMPLIFIED_CHINESE,"UTF-8");
            conf.setNumberFormat("###############");
            conf.setBooleanFormat("true,false");
            Template template = conf.getTemplate(tempName);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
