package com.cy.test.controller.rocedar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.test.bean.MqBean;
import com.cy.test.result.JsonObjectResult;
import com.cy.test.service.rocedar.DongyaService;
import com.cy.util.CyUtil;

import net.sf.json.JSONObject;

@RestController
public class DongyaController {
    private Logger logger = LogManager.getLogger(DongyaController.class);

    @Autowired
    private DongyaService dongyaService;

    /**
     * 根据手机号查询userid
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/dongya/getUseridByPhone/", method = RequestMethod.GET)
    public JsonObjectResult getUseridByPhone(@RequestParam(value = "phone", defaultValue = "-1") String phone) {
        dongyaService.getUseridByPhone(phone);
        JSONObject result = new JSONObject();
        result.put("userid", dongyaService.getUseridByPhone(phone));
        return new JsonObjectResult(0, result);
    }

    /**
     * 动吖内网测试环境-设备列表接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/dongya/getdevices/test22/", method = RequestMethod.GET)
    public JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "indicator_id", defaultValue = "-1") int taskIndicatorId,
            @RequestHeader(value = "os", defaultValue = "-1") int osType) {
        String url = "http://192.168.18.25/rest/3.0/device/list/";
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("task_id", String.valueOf(taskIndicatorId)));
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
     * 获取设备数据,记录日志
     * 
     * @param startDate
     * @param endDate
     */
    @RequestMapping(value = "/dongya/getdevicedatalog/", method = RequestMethod.GET)
    public void getDeviceDateLog(@RequestParam(value = "startDate", defaultValue = "-1") String startDate,
            @RequestParam(value = "endDate", defaultValue = "-1") String endDate) {
        List<MqBean> mqBeanList = dongyaService.getMqParam(startDate, endDate);
        for(Iterator<MqBean> mqList = mqBeanList.iterator();mqList.hasNext();){
            MqBean mqBean = mqList.next();
            logger.debug(mqBean.getUserId()+"|"+mqBean.getDeviceId()+"|"+mqBean.getTaskId()+"|"+mqBean.getTargetTypeId()+"|"+mqBean.getStartDate()+"|"+mqBean.getEndDate());
            String url = "http://192.168.18.25/rest/3.0/device/getdata/";
            String response = "";
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("deviceId", mqBean.getDeviceId()));
            params.add(new BasicNameValuePair("userId", mqBean.getUserId()));
            params.add(new BasicNameValuePair("targetTypeId", mqBean.getTargetTypeId()));
            params.add(new BasicNameValuePair("startDate", mqBean.getStartDate()));
            params.add(new BasicNameValuePair("endDate", mqBean.getEndDate()));
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("os", "1");
            try {
                response = CyUtil.httpGet(url, params, headers);
                logger.debug("-----response={}", response);
            } catch (Exception e) {
                e.printStackTrace();
            }
           }
    }
}
