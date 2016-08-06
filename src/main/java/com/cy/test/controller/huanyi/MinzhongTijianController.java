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

import net.sf.json.JSONObject;

@RestController
public class MinzhongTijianController {

     private static final String IP_TEST = "http://192.168.18.25";
//    private static final String IP_TEST = "http://localhost:8080";
    private static Logger logger = LogManager.getLogger(MinzhongTijianController.class);

    /**
     * 测试设备列表接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/minzhong/test/", method = RequestMethod.GET)
    public JSONObject getMinzhongTest(@RequestParam(value = "thname", defaultValue = "-1") String thname,
            @RequestParam(value = "license", defaultValue = "-1") String license) {
        // String url = IP_TEST + "/hy/device/list/";s
        String url = "http://th.mztj.cn/index.php";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("thname", thname));
        params.add(new BasicNameValuePair("license", license));
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
     * 获取用户基础信息和主体建议数据
     * 
     * @param code 身份证（客户体检下号码）
     * @param barcode 条形码（客户体检条形码）
     * @return
     */
    @RequestMapping(value = "/fangzhou/minzhong/searchpage/", method = RequestMethod.GET)
    public JSONObject searchpage(@RequestParam(value = "code", defaultValue = "-1") String code,
            @RequestParam(value = "barcode", defaultValue = "-1") String barcode) {
        String url = "http://th.mztj.cn/login.php";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("barcode", barcode));
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
     * 获取体检数据接口
     * 
     * @param barcode 条形码（客户体检条形码）
     * @return
     */
    @RequestMapping(value = "/fangzhou/minzhong/get/data/", method = RequestMethod.GET)
    public JSONObject getData(@RequestParam(value = "barcode", defaultValue = "-1") String barcode) {
//        String url = "http://th.mztj.cn/bgjson.php";
        String url = IP_TEST + "/hy/device/checkup/minzhong/report/data/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("barcode", barcode));
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
     * 获取体检报告页面接口
     * 
     * @param barcode 条形码（客户体检条形码）
     * @return
     */
    @RequestMapping(value = "/fangzhou/minzhong/get/html/", method = RequestMethod.GET)
    public void getHtml(@RequestParam(value = "barcode", defaultValue = "-1") String barcode) {
        String url = "http://th.mztj.cn/bg.php";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("barcode", barcode));
        try {
            response = CyUtil.httpPost(url, params, null, null);
            logger.debug("-----response={}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 获取个人体检报告列表
     * 
     * @param barcode 条形码（客户体检条形码）
     * @return
     */
    @RequestMapping(value = "/fangzhou/minzhong/get/report/list/", method = RequestMethod.GET)
    public void getReportList(@RequestParam(value = "code", defaultValue = "-1") String code) {
//        String url = "http://th.mztj.cn/query.php";
        String url = IP_TEST + "/hy/device/tijian/minzhong/report/list/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("code", code));
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
