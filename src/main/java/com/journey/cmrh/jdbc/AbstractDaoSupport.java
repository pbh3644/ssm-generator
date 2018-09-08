package com.journey.cmrh.jdbc;

import com.journey.cmrh.config.DbConfig;
import com.journey.cmrh.config.SetupConfig;
import com.journey.cmrh.core.Column;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author pangbohuan
 * @description 数据库连接配置父类
 * @date 2018-08-20 14:03
 **/
public abstract class AbstractDaoSupport {
    private final static String ORACLE = "oracle";

    /**
     * Load the JDBC driver
     */
    protected static String driverName = "";
    /**
     * a JDBC url
     */
    protected static String url = "";
    protected static String username = "";
    protected static String password = "";


    /**
     * init config
     * */
    static {
        DbConfig dbConfig = SetupConfig.getInstance().getDbConfig();
        driverName = dbConfig.getDriverClass();
        url = dbConfig.getUrl();
        username = dbConfig.getUsername();
        password = dbConfig.getPassword();
    }

    public static AbstractDaoSupport getInstance() {
        if (driverName.contains(ORACLE)) {
            return new OracleDao();
        }
        return new MysqlDao();
    }


    public List<String> queryAllTables(String nativeSql) {
        List<String> list = new ArrayList<String>();
        try {
            checkDriver();
            Connection conn = getConn();
            ResultSet rs = createQuary(conn, nativeSql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有的表
     *
     * @return List
     */
    public abstract List<String> queryAllTables();

    /**
     * 查询表中所有的列
     *
     * @param tableName 表名
     * @return List
     */
    public abstract List<Column> queryColumns(String tableName);

    /**
     * 查询表中的列对应的数据类型
     *
     * @param sqlType 字段名
     * @return String 类型
     */
    public abstract String typesConvert(String sqlType);


    protected ResultSet createQuary(Connection conn, String sql) throws SQLException {
        return conn.createStatement().executeQuery(sql);
    }

    protected Connection getConn() throws SQLException {
        return (Connection) DriverManager.getConnection(url, username, password);
    }

    protected void checkDriver() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
