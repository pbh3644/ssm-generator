package com.pbh.journey.system.app;


import com.pbh.journey.system.app.config.SetupConfig;
import com.pbh.journey.system.app.config.TemplateMapping;
import com.pbh.journey.system.app.core.BuildFactory;
import com.pbh.journey.system.app.jdbc.AbstractDaoSupport;
import com.pbh.journey.system.app.util.MyUtils;
import com.pbh.journey.system.app.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具入口
 *
 * @author lh
 * @version 1.0
 * @since 2018-08-11
 */
public class Builder {

    /**
     * beetl factory
     */
    private static BuildFactory factory = new BuildFactory();
    /**
     * config instance
     */
    private static SetupConfig config = SetupConfig.getInstance();

    /**
     * 待生成表集合
     */
    private static String[] tables = {
            "application",
            "organization"

//            "product_category_config",
//            "product_photo",
//            "product_sales_config",
//            "product_sku",
//            "merchant_location",
//            "merchant_product"
    };


    public void db2entry() {
        // iterator all template file
        TemplateMapping[] mappings = config.getMappings();
        List<String> tablesList = (tables == null || tables.length == 0) ? AbstractDaoSupport.getInstance().queryAllTables() : Arrays.asList(tables);
        for (TemplateMapping m : mappings) {
            // iterator all databases tables.
            for (String tableName : tablesList) {
                String packagePath = m.buildPackage(config.getProject(), config.getPackagePath(), config.getModel());
                String entityPackage = config.getEntityPackage();
                Map<String, Object> data = factory.getParams(tableName, packagePath, entityPackage);
                String className = StringUtil.className(tableName.replace(config.getIgnorePrefix(), ""));
                factory.build(MyUtils.getTemplatePath(m), data, MyUtils.getOutPutPath(m, className));
            }
        }
    }


    public static void main(String[] args) {
        BuildFactory.setLoadingDir(System.getProperty("user.dir") + "/src/main/resources/");
        Builder builder = new Builder();
        builder.db2entry();
        System.out.println("代码已生成!");
    }
}
