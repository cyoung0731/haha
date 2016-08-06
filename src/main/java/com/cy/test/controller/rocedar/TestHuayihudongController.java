
package com.cy.test.controller.rocedar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
import com.cy.util.DeviceUtil;
import com.cy.util.MessageUtils;

import net.sf.json.JSONObject;

@RestController
public class TestHuayihudongController {

    private static final int APP_ID = 1758512013;

    private Logger logger = LogManager.getLogger(TestHuayihudongController.class);

    @RequestMapping(value = "/huayihudong/search/package/", method = RequestMethod.GET)
    public void searchPackageByMobile(@RequestParam(value = "mobile", defaultValue = "-1") String mobile) {
        String logDesc = "根据手机号查询对应的套餐包";
        String url = "http://api.liuliangkong.cn/package/mobile_query";
        JSONObject paramJson = new JSONObject();
        paramJson.put("app_id", APP_ID);
        paramJson.put("mobile", mobile);
        paramJson.put("sign", getSign(paramJson));
        @SuppressWarnings("unchecked")
        Iterator<String> keys = paramJson.keys();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        while (keys.hasNext()) {
            String key = keys.next();
            params.add(new BasicNameValuePair(key, paramJson.getString(key)));
        }
        try {
            String result = DeviceUtil.httpPost(url, params, null, null);
            logger.debug(logDesc + "返回结果={}", result);
            logger.debug(logDesc + "返回结果转码后={}", CyUtil.ascii2Native(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 流量订购
     * 
     * @param mobile
     * @param pid
     */
    @RequestMapping(value = "/huayihudong/dinggou/", method = RequestMethod.GET)
    public void dinggou(@RequestParam(value = "mobile", defaultValue = "-1") String mobile,
            @RequestParam(value = "pid", defaultValue = "-1") String pid) {
        String logDesc = "流量订购";
        String url = "http://api.liuliangkong.cn/package/dinggou";
        String callbakUrl = "http://www.baidu.com";
        JSONObject paramJson = new JSONObject();
        paramJson.put("app_id", APP_ID);
        paramJson.put("mobile", mobile);
        paramJson.put("pid", pid);
        paramJson.put("statement", "test");
        paramJson.put("callback", callbakUrl);
        paramJson.put("sign", getSign(paramJson));
        @SuppressWarnings("unchecked")
        Iterator<String> keys = paramJson.keys();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        while (keys.hasNext()) {
            String key = keys.next();
            params.add(new BasicNameValuePair(key, paramJson.getString(key)));
        }
        try {
            String result = DeviceUtil.httpPost(url, params, null, null);
            logger.debug(logDesc + "返回结果={}", result);
            logger.debug(logDesc + "返回结果转码后={}", CyUtil.ascii2Native(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/huayihudong/dinggou/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "code", defaultValue = "-1") String code,
            @RequestParam(value = "order_id", defaultValue = "-1") String orderId,
            @RequestParam(value = "status", defaultValue = "-1") String status) {
//        String logDesc = "流量订购回调方法";
        if(code != null && orderId != null && status != null ){
            return "1";
        } else {
            return "0";
        }
    }

    private String getSign(JSONObject paramJson) {
        @SuppressWarnings("unchecked")
        List<String> keys = new ArrayList<String>(paramJson.keySet());
        Collections.sort(keys);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = paramJson.getString(key);
            str.append((i == 0 ? "" : "_") + key + "=" + value);
        }
        str.append("_" + APP_ID);
        return MessageUtils.toMD5Hex(str.toString());
    }

}
