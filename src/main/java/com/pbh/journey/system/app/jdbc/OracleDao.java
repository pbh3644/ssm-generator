package com.pbh.journey.system.app.jdbc;

import com.pbh.journey.system.app.core.Column;
import com.pbh.journey.system.app.util.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * @author pangbohuan
 * @description oracle
 * @date 2018-08-20 14:03
 **/
public class OracleDao extends AbstractDaoSupport {

    private final static String VARCHAR = "varchar";
    private final static String CHAR = "char";
    private final static String LONG = "long";
    private final static String NUMBER = "number";
    private final static String DATE = "date";

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
        if (oracleType.startsWith(VARCHAR) || oracleType.startsWith(CHAR)) {
            return "String";
        } else if (oracleType.startsWith(LONG)) {
            return "Integer";
        } else if (oracleType.startsWith(NUMBER)) {
            return "Double";
        } else if (oracleType.startsWith(DATE)) {
            return "Date";
        }
        return oracleType;
    }
}
