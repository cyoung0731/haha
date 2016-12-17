package com.cy.test.controller.huanyi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.test.cache.UserCacheFangzhou;
import com.cy.test.result.JsonObjectResult;
import com.cy.test.service.huanyi.FangzhouService;
import com.cy.util.CyUtil;
import com.cy.util.DeviceConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class FangzhouController {

    private static Logger logger = LogManager.getLogger(FangzhouController.class);
    @Autowired
    private FangzhouService fangzhouService;

    /**
     * 根据手机号查询userid
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/getUseridByPhone/", method = RequestMethod.GET)
    public JsonObjectResult getUseridByPhone(@RequestParam(value = "phone", defaultValue = "-1") String phone) {
        fangzhouService.getUseridByPhone(phone);
        JSONObject result = new JSONObject();
        String userId = fangzhouService.getUseridByPhone(phone);
		result.put("userid", userId);
        try {
			String token = UserCacheFangzhou.getUserCache().getTokenByUserId(Long.valueOf(userId));
			result.put("token", token);
		} catch (Exception e) {
			result.put("token", "获取token出错");
			return new JsonObjectResult(-1, result);
		} 
        return new JsonObjectResult(0, result);
    }

    /**
     * 测试设备列表接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/getdevices/", method = RequestMethod.GET)
    public JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "indicator_id", defaultValue = "-1") int indicator_id,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "os", defaultValue = "-1") int osType) {
        // String url = "http://localhost:8080/hy/device/list/";
        // String url = "http://192.168.18.25/hy/device/list/";
        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/list/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(indicator_id)));
        params.add(new BasicNameValuePair("status", String.valueOf(status)));
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
     * 测试设备列表接口-按指标分
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/getdevices/byindicator", method = RequestMethod.GET)
    public JSONObject getDevicesByIndicator(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "indicator_id", defaultValue = "-1") int indicator_id,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "os", defaultValue = "-1") int osType) {
        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/list/byindicator/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(indicator_id)));
        params.add(new BasicNameValuePair("status", String.valueOf(status)));
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

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/bind/oauth2/";
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
     * 测试绑定SN
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/bind/sn/", method = RequestMethod.GET)
    public JSONObject bindSn(@RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "sn", defaultValue = "-1") String code,
            @RequestParam(value = "token", defaultValue = "-1") String token) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/bind/sn/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("sn", String.valueOf(code)));
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
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
     * 测试绑定蓝牙
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/bind/bluetooth/", method = RequestMethod.GET)
    public JSONObject bindTooth(@RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "mac_addr", defaultValue = "-1") String macAddr,
            @RequestParam(value = "token", defaultValue = "-1") String token) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/bind/bluetooth/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("mac_addr", String.valueOf(macAddr)));
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
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
            @RequestParam(value = "token", defaultValue = "-1") String token) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/unbind/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("token", token));
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

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/getdata/";
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
    public void uploadData(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "indicator_id", defaultValue = "-1") int indicatorId,
            @RequestParam(value = "value", defaultValue = "-1") String value,
            @RequestParam(value = "derive_data", defaultValue = "") String deriveData,
            @RequestParam(value = "record_date", defaultValue = "-1") long recordDate,
            @RequestParam(value = "os", defaultValue = "-1") int osType) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/upload/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(indicatorId)));
        params.add(new BasicNameValuePair("value", value));
        params.add(new BasicNameValuePair("derive_data", String.valueOf(deriveData)));
        params.add(new BasicNameValuePair("record_date", String.valueOf(recordDate)));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传步数
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/upload/step/", method = RequestMethod.POST)
    public void uploadStepData(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "steps", defaultValue = "-1") String value) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/upload/steps/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("steps", value));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传血压
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/upload/bloodpressure/", method = RequestMethod.POST)
    public void uploadBloodPresureData(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "device_id", defaultValue = "-1") int deviceId,
            @RequestParam(value = "high_pressure", defaultValue = "-1") String highPressure,
            @RequestParam(value = "low_pressure", defaultValue = "-1") String lowPressure,
            @RequestParam(value = "heart_rate", defaultValue = "-1") String heartReat) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/upload/bloodpressure/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        params.add(new BasicNameValuePair("high_pressure", highPressure));
        params.add(new BasicNameValuePair("low_pressure", lowPressure));
        params.add(new BasicNameValuePair("heart_rate", heartReat));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传血糖设备数据-康为
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/upload/bloodsugar/kangwei/", method = RequestMethod.POST)
    public void uploadKangweiBloodsugarData(@RequestParam(value = "data_tamp", defaultValue = "-1") String dataTamp,
            @RequestParam(value = "value", defaultValue = "-1") String value,
            @RequestParam(value = "time_type", defaultValue = "-1") String timeType) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/upload/bloodsugar/kangwei/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pid", "0aeba513c616b94199b897ccd1211bd2"));
        params.add(new BasicNameValuePair("machine_id", "efeec6b62451bc91a913ba49602baaad"));
        params.add(new BasicNameValuePair("machine_type", "02"));
        params.add(new BasicNameValuePair("time_type", timeType));
        params.add(new BasicNameValuePair("data_tamp", dataTamp));
        params.add(new BasicNameValuePair("blood_glucose", value));
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传血糖设备数据-其他
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/upload/bloodsugar/", method = RequestMethod.POST)
    public void uploadBloodsugarData(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "device_id", defaultValue = "-1") String deviceId,
            @RequestParam(value = "time_type", defaultValue = "-1") String timeType,
            @RequestParam(value = "date", defaultValue = "-1") String date,
            @RequestParam(value = "blood_glucose", defaultValue = "-1") String bloodGlucose) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/upload/bloodsugar/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("device_id", deviceId));
        params.add(new BasicNameValuePair("time_type", timeType));
        params.add(new BasicNameValuePair("date", date));
        params.add(new BasicNameValuePair("blood_glucose", bloodGlucose));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户当前在用的指标设备
     * 
     * @param token
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/get/using/devicedata/", method = RequestMethod.GET)
    public JSONObject getAllUsingDeviceByUserId(@RequestParam(value = "token", defaultValue = "-1") String token) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/get/using/devicedata/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("days", "2"));
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
            return JSONObject.fromObject(response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.fromObject("{-1,\"异常\"}");
        }
    }

    /**
     * 修改用户指标设备
     *
     * @param token
     * @param indicatorId
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/indicator/update/", method = RequestMethod.POST)
    public JSONObject updateUseIndicatorDevice(@RequestParam(value = "token") String token,
            @RequestParam(value = "indicatorId") int indicatorId, @RequestParam(value = "deviceId") int deviceId) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/indicator/update/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(indicatorId)));
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            return JSONObject.fromObject(response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.fromObject("{-1,\"异常\"}");
        }
    }

    /**
     * 修改用户指标设备
     *
     * @param token
     * @param indicatorId
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/fangzhou/device/indicator/delete/", method = RequestMethod.POST)
    public JSONObject deleteUseIndicatorDevice(@RequestParam(value = "token") String token,
            @RequestParam(value = "indicatorId") int indicatorId, @RequestParam(value = "deviceId") int deviceId) {

        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/indicator/delete/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", String.valueOf(token)));
        params.add(new BasicNameValuePair("indicator_id", String.valueOf(indicatorId)));
        params.add(new BasicNameValuePair("device_id", String.valueOf(deviceId)));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
            return JSONObject.fromObject(response);
            // JSONArray responseJsonArray = JSONArray.fromObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONObject.fromObject("{-1,\"异常\"}");
        }
    }
}
