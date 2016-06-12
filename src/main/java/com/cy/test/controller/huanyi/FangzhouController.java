package com.cy.test.controller.huanyi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.util.CyUtil;

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
            @RequestParam(value = "task_id", defaultValue = "-1") int taskId,
            @RequestHeader(value = "os", defaultValue = "-1") int osType) {
        String url = "http://localhost:8080/device/list/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("task_id", String.valueOf(taskId)));
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
}
