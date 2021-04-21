package com.goddess.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Properties {

    static java.util.Properties props;

    private Properties() {
    }

    private static void initProperties() {
        try {
            //读取配置文件，如果没有指定配置文件，则读取默认配置文件config.properties（先查找同级目录，没有再找resources目录）
            String envConfig = System.getProperty("config");
            if (envConfig == null || "".equals(envConfig)) {
                envConfig = "config.properties";
            }
            if (!envConfig.endsWith(".properties")) {
                envConfig += ".properties";
            }
            props = loadAllProperties(envConfig);
            //输出全部props属性
//            props.forEach((key, value) -> System.out.println("[Property] " + key + "=" + value));

        } catch (IOException e) {
            throw new RuntimeException("Load Properties error", e);
        }
    }

    public static java.util.Properties getProperties() {
        if (props == null) {
            initProperties();
        }
        return props;
    }

    public static String getProperty(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
    }
    public static List<String> getPropertyList(String listKey) {
        List<String> res = new ArrayList<>();
        getProperties().forEach((key, value) -> {
            if(key.toString().startsWith(listKey)){
                res.add((String) value);
            }
        });
        return res;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    public static java.util.Properties loadAllProperties(String resourceName) throws IOException {
        java.util.Properties properties = new java.util.Properties();
        InputStream is;
        //读取当前文件夹的
        if (new File(resourceName).exists()) {
            is = new FileInputStream(resourceName);
        } else {
            //读取resources文件夹的
            is = Properties.class.getClassLoader().getResourceAsStream(resourceName);
        }
        properties.load(is);
        return properties;
    }
}
