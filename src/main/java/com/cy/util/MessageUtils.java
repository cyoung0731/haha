/**
 * CopyRight (c) 2016 北京瑰柏科技有限公司 保留所有权利
 */

package com.cy.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.SystemException;

public class MessageUtils {

    private static Logger logger = LogManager.getLogger(MessageUtils.class);

    @SuppressWarnings("finally")
    public static String urlEncode(String url) {
        String strValue = null;
        try {
            strValue = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return strValue;
        }
    }

    public static byte[] toMD5(String datas, String charset) {
        try {
            return toMD5(datas.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串解析错误", e);
        }
        return null;
    }

    public static String toMD5Hex(String datas) throws SystemException {
        return toMD5Hex(datas, "UTF-8");
    }

    public static String toMD5Hex(String datas, String charset) {
        try {
            return toMD5Hex(datas.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串解析错误", e);
        }
        return charset;
    }

    public static byte[] toMD5(byte[] datas) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
            return md.digest(datas);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toMD5Hex(byte[] datas) {
        return StringUtil.toHexString(toMD5(datas));
    }

}
