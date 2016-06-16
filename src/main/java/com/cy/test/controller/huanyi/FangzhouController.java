package com.cy.test.controller.huanyi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.util.CyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class FangzhouController {

    private static Logger logger = LogManager.getLogger(FangzhouController.class);

    /**
     * 测试设备列表接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/getdevices/", method = RequestMethod.GET)
    public JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "task_indicator_id", defaultValue = "-1") int task_indicator_id,
            @RequestParam(value = "os", defaultValue = "-1") int osType) {
        String url = "http://localhost:8080/device/list/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(task_indicator_id)));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("os", String.valueOf(osType));
        try {
            response = CyUtil.httpGet(url, params, headers);
            logger.debug("-----response={}", response);
            JSONObject responseJson = JSONObject.fromObject(response);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 测试绑定
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/bind/", method = RequestMethod.GET)
    public JSONObject bind(@RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "code", defaultValue = "-1") String code,
            @RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "relogin", defaultValue = "-1") int relogin) {
        
        String url = "http://localhost:8080/device/bind/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("code", String.valueOf(code)));
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("relogin", String.valueOf(relogin)));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            JSONObject responseJson = JSONObject.fromObject(response);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 测试解绑
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/unbind/", method = RequestMethod.POST)
    public JSONObject bind(@RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "user_id", defaultValue = "-1") long userId) {
        
        String url = "http://localhost:8080/device/unbind/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("user_id", String.valueOf(userId)));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            JSONObject responseJson = JSONObject.fromObject(response);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取设备数据
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/getdevicedata/", method = RequestMethod.GET)
    public JSONArray mq(@RequestParam(value = "userId", defaultValue = "114576668386620038") long userId,
            @RequestParam(value = "deviceId", defaultValue = "1202001") int deviceId,
            @RequestParam(value = "indicatorId", defaultValue = "1000") int indicatorId,
            @RequestParam(value = "startDate", defaultValue = "20160425") int startDate,
            @RequestParam(value = "endDate", defaultValue = "20160425") int endDate) {
        
        String url = "http://localhost:8080/device/getdata/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", String.valueOf(userId)));
        params.add(new BasicNameValuePair("deviceId", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("indicatorId", String.valueOf(indicatorId)));
        params.add(new BasicNameValuePair("startDate", String.valueOf(startDate)));
        params.add(new BasicNameValuePair("endDate", String.valueOf(endDate)));
        
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
            JSONArray responseJsonArray = JSONArray.fromObject(response);
            return responseJsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 上传设备数据
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/upload/", method = RequestMethod.POST)
    public JSONArray uploadData(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "indicator_id", defaultValue = "-1") int indicatorId,
            @RequestParam(value = "value", defaultValue = "-1") int value,
            @RequestParam(value = "derive_data", defaultValue = "-1") String deriveData, 
            @RequestParam(value = "record_date", defaultValue = "-1") int recordDate, 
            @RequestParam(value = "os", defaultValue = "-1") int osType) {
        
        String url = "http://localhost:8080/device/upload/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(indicatorId)));
        params.add(new BasicNameValuePair("value", String.valueOf(value)));
        params.add(new BasicNameValuePair("derive_data", String.valueOf(deriveData)));
        params.add(new BasicNameValuePair("record_date", String.valueOf(recordDate)));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            JSONArray responseJsonArray = JSONArray.fromObject(response);
            return responseJsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
