package com.pbh.journey.system.app.config;


/**
 * @author pangbohuan
 * @description Group
 * @date 2018-08-20 14:03
 **/
public class Group {

    private String name;
    private String prefix;


    public String findGroupName(String tableName) {
        String[] ps = prefix.split(",");
        for (String s : ps) {
            if (tableName.startsWith(s)) {
                return this.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
