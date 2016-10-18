package com.cy.test.controller.huanyi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.util.CyUtil;
import com.cy.util.DeviceConstants;
import com.cy.util.DeviceUtil;

import net.sf.json.JSONObject;

@RestController
public class BeitaiController {

    private static Logger logger = LogManager.getLogger(BeitaiController.class);

    /**
     * 测试倍泰扫描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/aio/beitai/scan/", method = RequestMethod.GET)
    public JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "user_phone", defaultValue = "-1") String user_phone,
            @RequestParam(value = "user_idcard", defaultValue = "-1") String user_idcard,
            @RequestParam(value = "report_id", defaultValue = "-1") String report_id,
            @RequestParam(value = "appid", defaultValue = "-1") int appid,
            @RequestParam(value = "report_time", defaultValue = "-1") String report_time) {
        String url = "";
        if (appid == 1) {
            url = DeviceConstants.IP_TEST_DY + "/device/aio/beitai/scan/";
        } else if (appid == 2) {
            url = DeviceConstants.IP_TEST_FZ + "/aio/beitai/";
        }
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("user_phone", user_phone));
        params.add(new BasicNameValuePair("user_idcard", user_idcard));
        params.add(new BasicNameValuePair("report_id", report_id));
        params.add(new BasicNameValuePair("report_time", report_time));
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
            JSONObject responseJson = JSONObject.fromObject(response);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试倍泰扫描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/aio/beitai/upload/", method = RequestMethod.GET)
    public JSONObject uploadReport(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "user_phone", defaultValue = "-1") String user_phone,
            @RequestParam(value = "user_idcard", defaultValue = "-1") String user_idcard,
            @RequestParam(value = "report_id", defaultValue = "-1") String reportid,
            @RequestParam(value = "report_time", defaultValue = "-1") String report_time) {

        String url = DeviceConstants.IP_TEST_DIS + "/device/aio/report/beitai/";

        reportid = "\"" + reportid + "\"";

        // 体温
        String temp = "{\"data\":{\"medicId\":\"102546\",\"physicalID\":" + reportid
                + ",\"temperature\":\"88.5\",\"time\":1460054041668},\"did\":\"eeeee\",\"dtype\":\"09\",\"idcard\":null,\"phone\":\"13245679854\",\"type\":3}";
        // 血压
        String bp = "{\"data\":{\"diastolic\":78,\"medicId\":\"528A7B3AEE1F4A8BA269139E2999EE11\",\"physicalID\":"
                + reportid
                + ",\"pulse\":65,\"systolic\":123,\"time\":\"2016-05-06 13:54:29\"},\"did\":\"JKJ1512053117001573\",\"dtype\":\"9\",\"idcard\":\"421124198711208970\",\"phone\":\"13492730728\",\"type\":7}";
        // 身高体重
        String weight = "{\"data\":{\"height\":\"186\",\"medicId\":\"102546\",\"physicalID\":" + reportid
                + ",\"time\":1460054618019,\"weight\":\"88\"},\"did\":\"eeeee\",\"dtype\":\"09\",\"idcard\":null,\"phone\":\"13245679854\",\"type\":9}";
        // 人体成分
        String fat = "{\"data\":{\"adiposerate\":\"34.0\",\"basalMetabolism\":1446,\"bmi\":\"23.3\",\"bone\":\"2.6\",\"medicId\":\"528A7B3AEE1F4A8BA269139E2999EE11\",\"metabolism\":1909,\"moisture\":\"48.2\",\"muscle\":\"37.8\",\"physicalID\":"
                + reportid
                + ",\"time\":\"2016-05-04 14:58:38\",\"visceralfat\":9},\"did\":\"ohhmei\",\"dtype\":\"9\",\"idcard\":null,\"phone\":\"15016205948\",\"type\":8}";
        // 血氧
        String bo = "{\"data\":{\"bloodoxygen\":97,\"medicId\":\"528A7B3AEE1F4A8BA269139E2999EE11\",\"physicalID\":"
                + reportid
                + ",\"pulse\":91,\"time\":\"2016-05-06 15:20:42\"},\"did\":\"JKJ1512053117001573\",\"dtype\":\"9\",\"idcard\":\"421124198711208970\",\"phone\":\"13492730728\",\"type\":5}";
        try {
            String responseBp = DeviceUtil.httpPost(url, null, null, bp);
            logger.debug("上传血压返回结果=" + responseBp);
            String responseBo = DeviceUtil.httpPost(url, null, null, bo);
            logger.debug("上传血氧返回结果=" + responseBo);
            String responseTemp = DeviceUtil.httpPost(url, null, null, temp);
            logger.debug("上传体温返回结果=" + responseTemp);
            String responseBf = DeviceUtil.httpPost(url, null, null, weight);
            logger.debug("上传身高体重返回结果=" + responseBf);
            String responseFat = DeviceUtil.httpPost(url, null, null, fat);
            logger.debug("上传人体成分重返回结果=" + responseFat);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
