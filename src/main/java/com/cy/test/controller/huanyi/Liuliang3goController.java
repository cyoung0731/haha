package com.cy.test.controller.huanyi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cy.util.CyUtil;
import com.cy.util.MessageUtils;

import net.sf.json.JSONObject;

@RestController
public class Liuliang3goController {

    private static Logger logger = LogManager.getLogger(Liuliang3goController.class);
    
    private static final String PLATFORM_ADDR = "http://new.3gjiayou.com";
    private static final String ID = "18600489361"; //账号
    private static final String PASSWORD = "888888";
    private static final String KEY = "F9985B6EC97ACF3C3D5DA8990AF89A15";
    private static final String ACCOUNT = "100000005";

    /**
     * 测试琥蜂扫描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/liuliang/balance/query/", method = RequestMethod.GET)
    public JSONObject getDevices() {
        // String url = IP_TEST + "/hy/device/aio/beitai/scan/";
        String url = PLATFORM_ADDR + "/com/rwxz/biz/company/company/getBalance.do";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("account", ACCOUNT));
        String sign = MessageUtils.toMD5Hex(ACCOUNT+KEY);
        params.add(new BasicNameValuePair("sign", sign));
        
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
}
