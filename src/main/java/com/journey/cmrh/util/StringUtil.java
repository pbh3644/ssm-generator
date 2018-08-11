package com.journey.cmrh.util;

import com.journey.cmrh.config.SetupConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lh
 */
public class StringUtil {

    public static String capFirst(String str) {
        String firstC = str.substring(0, 1);

        return str.replaceFirst(firstC, firstC.toUpperCase());
    }


    public static String javaStyle(String columnName) {
        String patternStr = "(_[a-z,A-Z])";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(columnName.toLowerCase());
        StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
            String replaceStr = matcher.group();
            matcher.appendReplacement(buf, replaceStr.toUpperCase());
        }
        matcher.appendTail(buf);
        return buf.toString().replaceAll("_", "");
    }

    //去前缀
    public static String javaStyleOfTableName(String tableName) {
        String prefixs = SetupConfig.getInstance().getIgnorePrefix();
        String[] ps = prefixs.split(",");
        for (int i = 0; i < ps.length; i++) {
            if (tableName.startsWith(ps[i])) {
                tableName = tableName.replaceAll(ps[i], "");
            }
        }
        return StringUtil.javaStyle(tableName);
    }


    // 类名
    public static String className(String tableName) {
        //return capFirst(javaStyleOfTableName(tableName));
        return capitalize(toUpper(tableName));
    }

    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
            return str;
        else
            return (new StringBuilder(strLen))
                    .append(Character.toTitleCase(str.charAt(0)))
                    .append(str.substring(1)).toString();
    }

    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
            return str;
        else
            return (new StringBuilder(strLen))
                    .append(Character.toLowerCase(str.charAt(0)))
                    .append(str.substring(1)).toString();
    }

    /**
     * “_”+小写 转成大写字母
     *
     * @param str
     * @return
     */
    private static String toUpper(String str) {
        char[] charArr = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '_') {
                sb.append(String.valueOf(charArr[i + 1]).toUpperCase());
                i = i + 1;
            } else {
                sb.append(charArr[i]);
            }
        }
        return sb.toString();
    }
}
