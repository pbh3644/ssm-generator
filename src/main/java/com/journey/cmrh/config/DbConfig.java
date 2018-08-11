package com.journey.cmrh.config;


import lombok.Data;

/**
 * 数据库相关配置
 *
 * @author lh
 * @version 3.0
 */
@Data
public class DbConfig {

    private String driverClass;
    private String username;
    private String password;
    private String url;
}
