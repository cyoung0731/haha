
package com.cy.util;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

    /**
     * 判断字符串是否为空
     * 
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        return value == null || value.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     * 
     * @param value
     * @return
     */
    public static boolean isNotNull(String value) {
        return value != null && value.length() > 0;
    }

    /**
     * 转16进制
     * 
     * @param datas
     * @return
     */
    public static String toHexString(byte[] datas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            String hex = Integer.toHexString(datas[i] & 0xFF);
            if (hex.length() <= 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * list转json字符串
     * 
     * @param list
     * @return
     */
    public static String toJson(List<?> list) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(',');
            }
            Object o = list.get(i);
            if (o instanceof String) {
                String os = (String) o;
                // " => \", however regex expression need double, so it become
                // complex
                os = os.replaceAll("\"", "\\\\\"");
                sb.append('"').append(os).append('"');
            } else {
                sb.append(list.get(i));
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * set转json字符串
     * 
     * @param set
     * @return
     */
    public static String toJson(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (!set.isEmpty()) {
            for (String key : set) {
                sb.append("\"").append(key).append("\",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * 连接字符串
     *
     * @param center
     * @param objects
     * @return
     */
    public static String concat(String center, Object... objects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                continue;
            }
            sb.append(objects[i]);
            if (i == (objects.length - 1)) {
                continue;
            }
            if (!isEmpty(center)) {
                sb.append(center);
            }
        }
        return sb.toString();
    }

    /**
     * 获取字符串编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

}
