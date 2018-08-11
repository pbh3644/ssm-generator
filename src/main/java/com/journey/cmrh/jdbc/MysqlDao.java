package com.journey.cmrh.jdbc;

import com.journey.cmrh.core.Column;
import com.journey.cmrh.util.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * @author
 */
public class MysqlDao extends AbstractDaoSupport {
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
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")) {
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        }
        return "String";
    }

    //@Override
    @Deprecated
    public String typesConverts(String mysqlType) {
        mysqlType = mysqlType.substring(0, mysqlType.indexOf("(") == -1 ? mysqlType.length() : mysqlType.indexOf("("));

        if (mysqlType.equalsIgnoreCase("varchar") || mysqlType.equalsIgnoreCase("char")
                || mysqlType.equalsIgnoreCase("nvarchar") || mysqlType.equalsIgnoreCase("nchar")
                || mysqlType.equalsIgnoreCase("text") || mysqlType.startsWith("longtext")) {
            return "String";
        } else if (mysqlType.startsWith("int")) {
            return "Integer";
        } else if (mysqlType.startsWith("bigint")) {
            return "Long";
        } else if (mysqlType.startsWith("double")) {
            return "Double";
        } else if (mysqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (mysqlType.startsWith("tinyint")) {
            return "Byte";
        } else if (mysqlType.equalsIgnoreCase("datetime") || mysqlType.equalsIgnoreCase("date")
                || mysqlType.startsWith("timestamp")) {
            return "Date";
        } else if (mysqlType.equalsIgnoreCase("decimal") || mysqlType.equalsIgnoreCase("numeric")
                || mysqlType.equalsIgnoreCase("real") || mysqlType.equalsIgnoreCase("money")
                || mysqlType.equalsIgnoreCase("smallmoney") || mysqlType.equalsIgnoreCase("double")) {
            return "Double";
        }
        return mysqlType;
    }
}
