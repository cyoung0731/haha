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
public class HufengController {

    private static Logger logger = LogManager.getLogger(HufengController.class);

    /**
     * 测试琥蜂扫描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/aio/hufeng/scan/", method = RequestMethod.GET)
    public JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "dn", defaultValue = "-1") String dn,
            @RequestParam(value = "appid", defaultValue = "-1") int appid,
            @RequestParam(value = "tag2", defaultValue = "-1") String tag2) {
        String url = "";
        if(appid == 1){
            url =  DeviceConstants.IP_TEST_DY + "/device/aio/hufeng/scan/";
        } else if(appid == 2){
            url =  DeviceConstants.IP_TEST_FZ + "/hy/device/aio/hufeng/scan/";
        }
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("dn", dn));
        params.add(new BasicNameValuePair("tag2", tag2));
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
     * 测试琥蜂扫描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/aio/hufeng/upload/", method = RequestMethod.GET)
    public JSONObject uploadReport(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "dn", defaultValue = "-1") String dn,
            @RequestParam(value = "tag2", defaultValue = "-1") String tag2) {
        String url = DeviceConstants.IP_TEST_DIS + "/device/aio/report/hufeng/";
        
        String bp = "data={\"dn\":\"" + dn + "\",\"tag\":null,\"tag2\":\"" + tag2
                + "\",\"map\":82,\"oximetryPI\":0.0,\"glucoseCode\":null,\"timestamp\":1458894477000,\"diastolic\":100,\"glucose\":0.0,\"heartrate\":103,\"oximetry\":0.0,\"systolic\":135,\"temperature\":0.0,\"weight\":0.0,\"bioR\":0.0,\"signType\":\"bp\",\"usercode\":\"d4t4N0hizGtJhVLx89D7\",\"glucosePeriod\":null,\"continutity\":0}&usercode=d4t4N0hizGtJhVLx89D7";
        String bo = "data={\"dn\":\"" + dn + "\",\"tag\":null,\"tag2\":\"" + tag2
                + "\",\"map\":0,\"oximetryPI\":1.687,\"glucoseCode\":null,\"timestamp\":1458894477000,\"diastolic\":0,\"glucose\":0.0,\"heartrate\":71,\"oximetry\":98.0,\"systolic\":0,\"temperature\":0.0,\"weight\":0.0,\"bioR\":0.0,\"signType\":\"spo2\",\"usercode\":\"d4t4N0hizGtJhVLx89D7\",\"glucosePeriod\":null,\"continutity\":0}&usercode=d4t4N0hizGtJhVLx89D7";
        String temp = "data={\"dn\":\"" + dn + "\",\"tag\":null,\"tag2\":\"" + tag2
                + "\",\"map\":0,\"oximetryPI\":0.0,\"glucoseCode\":\"C00\",\"timestamp\":1458894477000,\"diastolic\":0,\"glucose\":20.3,\"heartrate\":0,\"oximetry\":0.0,\"systolic\":0,\"temperature\":34.0,\"weight\":0.0,\"bioR\":0.0,\"signType\":\"temp\",\"usercode\":\"d4t4N0hizGtJhVLx89D7\",\"glucosePeriod\":\"晚餐前2H\",\"continutity\":0}&usercode=d4t4N0hizGtJhVLx89D7";
        String bf = "data={\"dn\":\"" + dn + "\",\"tag\":null,\"tag2\":\"" + tag2
                + "\",\"map\":0,\"oximetryPI\":0.0,\"glucoseCode\":null,\"timestamp\":1458894477000,\"diastolic\":0,\"glucose\":0.0,\"heartrate\":0,\"oximetry\":0.0,\"systolic\":0,\"temperature\":0.0,\"weight\":65.3,\"bioR\":32.1,\"signType\":\"bf\",\"usercode\":\"d4t4N0hizGtJhVLx89D7\",\"glucosePeriod\":null,\"continutity\":0}&usercode=d4t4N0hizGtJhVLx89D7d4t4N0hizGtJhVLx89D7";

        try {
            String responseBp = DeviceUtil.httpPost(url, null, null, bp);
            logger.debug("上传血压返回结果="+responseBp);
            String responseBo = DeviceUtil.httpPost(url, null, null, bo);
            logger.debug("上传血氧返回结果="+responseBo);
            String responseTemp = DeviceUtil.httpPost(url, null, null, temp);
            logger.debug("上传体温返回结果="+responseTemp);
            String responseBf = DeviceUtil.httpPost(url, null, null, bf);
            logger.debug("上传体脂返回结果="+responseBf);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
