
package com.cy.util;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 * @version 1.0.0.2016年4月15日
 */
public class CyUtil {

    private static Logger logger = LogManager.getLogger(CyUtil.class);

    /**
     * 公用httpGet方法
     * 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpGet(String url, List<NameValuePair> params, Map<String, String> headers) throws Exception {
        URIBuilder uri = new URIBuilder(url);
        if (params != null) {
            uri.setParameters(params);
        }
        HttpGet hg = new HttpGet(uri.build().toString());
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                hg.addHeader(key, headers.get(key));
            }
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        response = httpClient.execute(hg);
        HttpEntity responseEntity = response.getEntity();
        String reponseString = EntityUtils.toString(responseEntity, "utf-8");
        return reponseString;
    }

    /**
     * 公用httpPost方法
     * 
     * @param url
     * @param params
     * @param headers
     * @param entity
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, List<NameValuePair> params, Map<String, String> headers, String entity)
            throws Exception {
        HttpPost hp = new HttpPost(url);
        if (params != null) {
            hp.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        }
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                hp.addHeader(key, headers.get(key));
            }
        }
        if (entity != null) {
            hp.setEntity(new StringEntity(entity, Charset.forName("UTF-8")));
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        response = httpClient.execute(hp);
        HttpEntity responseEntity = response.getEntity();
        String reponseString = EntityUtils.toString(responseEntity, "utf-8");
        return reponseString;
    }

    /**
     * 计算token具体到期时间
     * 
     * @param expiresIn
     * @return
     */
    public static Long getExpireTime(int expiresIn) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, expiresIn);
        Long expireTime = Long.parseLong(DateFormatUtils.format(now, "yyyyMMddHHmmss"));
        return expireTime;
    }

    /**
     * 时间戳转日期，格式化
     *
     * @param millisecond
     *        毫秒
     * @param format yyyyMMddHHmmss
     * @return
     */
    public static String timeStamp2Date(String millisecond, String format) {
        if (millisecond == null || millisecond.isEmpty() || millisecond.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(millisecond)));
    }


    /**
     * 设备数据写入日志文件
     *
     * @param userId
     * @param device_id
     * @param targetTypeId
     * @param value
     * @param deriveData
     * @param recordDate
     * @param deviceDataJson
     *        -- 调用设备接口返回原始
     */
    public static void saveDeviceDataLog(long userId, int device_id, int targetTypeId, String value, String deriveData,
            int recordDate, JSONObject deviceDataJson) {
        // 写入日志文件 start
        // JSONObject logJson = new JSONObject();
        // logJson.put("user_id", mqJson.getLong("userId"));
        // logJson.put("device_id", device_id);
        // logJson.put("target_type_id", targetTypeId);
        // logJson.put("start_date", mqJson.getString("startDate"));
        // logJson.put("end_date", mqJson.getString("endDate"));
        // logJson.put("value", value);
        // logJson.put("derive_data", resultJson.getString("recordDate"));
        // logJson.put("log", subJson.toString());
        // logger.fatal(logJson.toString());
        StringBuffer sb = new StringBuffer();
        sb.append(userId);
        sb.append("|");
        sb.append(device_id);
        sb.append("|");
        sb.append(targetTypeId);
        sb.append("|");
        sb.append(value);
        sb.append("|");
        sb.append(deriveData);
        sb.append("|");
        sb.append(recordDate);
        sb.append("|");
        if(deviceDataJson == null){
            sb.append("");
        } else {
            sb.append(deviceDataJson.toString());
        }
        logger.fatal(sb.toString());
        // 写入日志文件 end
    }

    /**
     * 格式化当前时间yyyyMMddHHmmss
     * 
     * @return
     */
    public static long formatNowtime(String style) {
        Calendar now = Calendar.getInstance();
        return Long.parseLong(DateFormatUtils.format(now, style));
    }

    // /**
    // * 时间戳转换成日期格式字符串
    // * @param seconds 精确到秒的字符串
    // * @param formatStr
    // * @return
    // */
    // public static String timeStamp2Date(String seconds,String format) {
    // if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
    // return "";
    // }
    // if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
    // SimpleDateFormat sdf = new SimpleDateFormat(format);
    // return sdf.format(new Date(Long.valueOf(seconds+"000")));
    // }
    /**
     * 日期格式字符串转换成时间戳
     * 
     * @param date
     *        字符串日期
     * @param format
     *        如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * 
     * @return
     */
    public static String nowtimeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * 格式化瑰柏日期（yyyymmdd）转换为（yyyy-mm-dd）
     *
     * @param date
     * @return
     */
    public static String formatGbDate(String date) {
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
    }
}
