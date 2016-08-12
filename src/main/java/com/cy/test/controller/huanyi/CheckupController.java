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

@RestController
public class CheckupController {

    private static Logger logger = LogManager.getLogger(CheckupController.class);

    /**
     * 测试设备列表接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/checkup/send/report/list/", method = RequestMethod.GET)
    public String getMinzhongTest(@RequestParam(value = "token", defaultValue = "-1") String token) {
         String url = DeviceConstants.IP_TEST_FZ + "/hy/device/send/report/list/";
        String response = "";
        try {
            response = CyUtil.httpGet(url, null, null);
            logger.debug("-----response={}", response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 测试设备列表接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/checkup/report/list/", method = RequestMethod.GET)
    public String getReportList(@RequestParam(value = "token", defaultValue = "-1") String token) {
        String url = DeviceConstants.IP_TEST_FZ + "/hy/device/report/list/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
