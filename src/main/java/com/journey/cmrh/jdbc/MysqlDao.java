package com.journey.cmrh.jdbc;

import com.journey.cmrh.core.Column;
import com.journey.cmrh.util.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * @author pangbohuan
 * @description mysql
 * @date 2018-08-20 14:03
 **/
public class MysqlDao extends AbstractDaoSupport {

    private final static String BIT = "bit";
    private final static String TINYINT = "tinyint";
    private final static String SMALLINT = "smallint";
    private final static String INT = "int";
    private final static String BIGINT = "bigint";
    private final static String FLOAT = "float";
    private final static String DECIMAL = "decimal";
    private final static String NUMERIC = "numeric";
    private final static String REAL = "real";
    private final static String MONEY = "money";
    private final static String SMALL_MONEY = "smallmoney";
    private final static String DOUBLE = "double";
    private final static String VARCHAR = "varchar";
    private final static String CHAR = "char";
    private final static String DATETIME = "datetime";
    private final static String DATE = "date";
    private final static String IMAGE = "image";


    @Override
    public List<String> queryAllTables() {
        return queryAllTables("show tables");
    }

    @Override
    public List<Column> queryColumns(String tableName) {
        List<Column> list = new ArrayList<Column>();
        try {
            checkDriver();
            Connection conn = getConn();
            ResultSet rs = createQuary(conn, "show full fields from " + tableName);
            while (rs.next()) {
                String type = typesConvert(rs.getString(2));
                String javaStyle = StringUtil.javaStyle(rs.getString(1));
                list.add(new Column(type, rs.getString(1), javaStyle, rs.getString(9)));
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    @Override
    public String typesConvert(String sqlType) {
        sqlType = sqlType.substring(0, sqlType.indexOf("(") == -1 ? sqlType.length() : sqlType.indexOf("("));
        if (sqlType.equalsIgnoreCase(BIT)) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase(TINYINT)) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase(SMALLINT)) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase(INT)) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase(BIGINT)) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase(FLOAT)) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase(DECIMAL) || sqlType.equalsIgnoreCase(NUMERIC)
                || sqlType.equalsIgnoreCase(REAL) || sqlType.equalsIgnoreCase(MONEY)
                || sqlType.equalsIgnoreCase(SMALL_MONEY) || sqlType.equalsIgnoreCase(DOUBLE)) {
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase(VARCHAR) || sqlType.equalsIgnoreCase(CHAR)
                || "nvarchar".equalsIgnoreCase(sqlType) || "nchar".equalsIgnoreCase(sqlType)
                || "text".equalsIgnoreCase(sqlType)) {
            return "String";
        } else if (sqlType.equalsIgnoreCase(DATETIME) || sqlType.equalsIgnoreCase(DATE)) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase(IMAGE)) {
            return "Blob";
        }
        return "String";
    }

    @Deprecated
    public String typesConverts(String mysqlType) {
        mysqlType = mysqlType.substring(0, mysqlType.indexOf("(") == -1 ? mysqlType.length() : mysqlType.indexOf("("));

        if (mysqlType.equalsIgnoreCase(VARCHAR) || mysqlType.equalsIgnoreCase(CHAR)
                || "nvarchar".equalsIgnoreCase(mysqlType) || "nchar".equalsIgnoreCase(mysqlType)
                || "text".equalsIgnoreCase(mysqlType) || "longtext".startsWith(mysqlType)) {
            return "String";
        } else if (mysqlType.startsWith(INT)) {
            return "Integer";
        } else if (mysqlType.startsWith(BIGINT)) {
            return "Long";
        } else if (mysqlType.startsWith(DOUBLE)) {
            return "Double";
        } else if (mysqlType.equalsIgnoreCase(BIT)) {
            return "Boolean";
        } else if (mysqlType.startsWith(TINYINT)) {
            return "Byte";
        } else if (mysqlType.equalsIgnoreCase(DATETIME) || mysqlType.equalsIgnoreCase(DATE)
                || mysqlType.startsWith("timestamp")) {
            return "Date";
        } else if (mysqlType.equalsIgnoreCase(DECIMAL) || mysqlType.equalsIgnoreCase(NUMERIC)
                || "real".equalsIgnoreCase(mysqlType) || "money".equalsIgnoreCase(mysqlType)
                || mysqlType.equalsIgnoreCase(SMALLINT) || "double".equalsIgnoreCase(mysqlType)) {
            return "Double";
        }
        return mysqlType;
    }
}
