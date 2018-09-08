package com.journey.cmrh.core;

import java.util.List;

/**
 * @author pangbohuan
 * @description 数据库映射JAVA
 * @date 2018-08-20 14:03
 **/
public class Column {



    /**
     * 列数据类型
     * */
    private String type;


    /**
     * 字段名
     * */
    private String name;

     /**
     * 字段名，java风格
     * */
    private String nameJ;

    /**
     * 备注，注释
     * */
    private String remark;


    public Column() {
    }

    public Column(String type, String name, String nameJ) {
        this.type = type;
        this.name = name;
        this.nameJ = nameJ;
    }

    public Column(String type, String name, String nameJ, String remark) {
        this.type = type;
        this.name = name;
        this.nameJ = nameJ;
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameJ() {
        return nameJ;
    }

    public void setNameJ(String nameJ) {
        this.nameJ = nameJ;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public static boolean typeContains(List<Column> columns, String type) {
        for (Column c : columns) {
            if (c.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Column{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", nameJ='" + nameJ + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
