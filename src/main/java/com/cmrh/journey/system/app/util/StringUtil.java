package com.cmrh.journey.system.app.util;

import com.cmrh.journey.system.app.config.SetupConfig;

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

    /**
     * 去前缀
     */
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


    /**
     * 类名
     */
    public static String className(String tableName) {
        //return capFirst(javaStyleOfTableName(tableName));
        return capitalize(toUpper(tableName));
    }

    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        } else {
            return (new StringBuilder(strLen))
                    .append(Character.toTitleCase(str.charAt(0)))
                    .append(str.substring(1)).toString();
        }
    }

    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        } else {
            return (new StringBuilder(strLen))
                    .append(Character.toLowerCase(str.charAt(0)))
                    .append(str.substring(1)).toString();
        }
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

    private static final char SEPARATOR = '_';

    /**
     * 是否为null或空串
     *
     * @param source 原文
     * @return true | false
     */
    public static boolean isEmpty(String source) {
        return source == null || source.isEmpty();
    }

    /**
     * 字符串不是null且不是空串
     *
     * @param source 原文
     * @return 是否不空 true | false
     */
    public static boolean isNotEmpty(String source) {
        return source != null && source.length() > 0;
    }

    /**
     * 指定位置替换为*
     *
     * @param source 原文
     * @param from   开始位，计数从1开始
     * @param to     结束位，计数从1开始
     * @return 如：abc12******0d
     */
    public static String replaceWithStar(String source, int from, int to) {
        if (isEmpty(source) || from > to) {
            return source;
        }
        from = from < 1 ? 1 : from;
        to = to > source.length() ? source.length() : to;
        StringBuilder sb = new StringBuilder();
        sb.append(source.substring(0, from - 1));
        for (int i = 0; i < to - from + 1; i++) {
            sb.append("*");
        }
        sb.append(source.substring(to));
        return sb.toString();
    }

    /**
     * 两边加%，用于sql中like
     *
     * @param str 原文
     * @return trim后两边加%
     */
    public static String turn2LikeStr(String str) {
        if (isEmpty(str)) {
            return "%%";
        }
        return "%" + str.trim() + "%";
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if (i > 0 && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
}
