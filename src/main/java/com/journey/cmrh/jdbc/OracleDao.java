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
public class OracleDao extends AbstractDaoSupport {
    @Override
    public List<String> queryAllTables() {
        return queryAllTables("select lower(tname) from tab where tabtype = 'TABLE' order by 1");

    }

    @Override
    public List<Column> queryColumns(String tableName) {
        List<Column> list = new ArrayList<Column>();
        try {
            checkDriver();
            Connection conn = getConn();
            String sql =
                    "select  lower(t1.column_name), lower(t1.data_type),  t2.comments " +
                            " from  user_col_comments  t2  left  join  user_tab_columns  t1 " +
                            " on  t1.table_name  =  t2.table_name  and  t1.column_name  =  t2.column_name " +
                            " where  t1.table_name  =  upper('" + tableName + "')";
            ResultSet rs = createQuary(conn, sql);
            while (rs.next()) {
                String type = typesConvert(rs.getString(2));
                String javaStyle = StringUtil.javaStyle(rs.getString(1));
                list.add(new Column(type, rs.getString(1), javaStyle, rs.getString(3)));
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String typesConvert(String oracleType) {
        if (oracleType.startsWith("varchar") || oracleType.startsWith("char")) {
            return "String";
        } else if (oracleType.startsWith("long")) {
            return "Integer";
        } else if (oracleType.startsWith("number")) {
            return "Double";
        } else if (oracleType.startsWith("date")) {
            return "Date";
        }
        return oracleType;
    }
}
