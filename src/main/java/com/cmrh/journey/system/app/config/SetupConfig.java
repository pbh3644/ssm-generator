package com.cmrh.journey.system.app.config;

import com.google.gson.Gson;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author pangbohuan
 * @description 配置文件
 * @date 2018-08-20 14:03
 **/
@Data
public class SetupConfig {
    private static SetupConfig instance;

    /**
     * project work dir
     */
    public static String USER_DIR = System.getProperty("user.dir");
    public static String RESOURCES_PATH = "/src/main/resources/";
    public static final String SEPARATOR = File.separator;

    private String project;
    private String packagePath;
    private String entityPackage;
    /**
     * default 'king'
     */
    private String author = "lh";
    private String model;

    private String ignorePrefix;

    private DbConfig dbConfig;
    private String templateDir;
    private TemplateMapping[] mappings;
    private Group[] groups;

    private static String loadJson() {
        StringBuilder sb = new StringBuilder("");
        try {
            BufferedReader in = new BufferedReader(new FileReader(USER_DIR + RESOURCES_PATH + "config-ssm.json"));
            String str = "";
            while ((str = in.readLine()) != null) {
                // 处理行注释
                int contentIndex = str.indexOf("//");
                if (contentIndex != -1) {
                    str = str.substring(0, contentIndex);
                }
                sb.append(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("找不到配置文件:" + USER_DIR + RESOURCES_PATH + "config-ssm.json");
        }
        return sb.toString();
    }


    public static SetupConfig getInstance() {
        if (instance == null) {
            instance = new Gson().fromJson(loadJson(), SetupConfig.class);
        }
        return instance;
    }
}
