package com.journey.cmrh.config;


import lombok.Data;

/**
 * @author pangbohuan
 * @description 数据库相关配置
 * @date 2018-08-20 14:03
 **/
@Data
public class DbConfig {

    private String driverClass;
    private String username;
    private String password;
    private String url;
}
