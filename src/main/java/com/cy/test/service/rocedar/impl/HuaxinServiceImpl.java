
package com.cy.test.service.rocedar.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.xml.DOMParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cy.test.result.BasicResult;
import com.cy.test.service.rocedar.HuaxinService;

@Service("huaxinService")
public class HuaxinServiceImpl implements HuaxinService {
    private Logger logger = LogManager.getLogger(HuaxinServiceImpl.class);
  
    
    @Override
    public BasicResult sendVerification(List<String> phones, String verification) {
       return sendVerification(String.join(",", phones), verification);
//        for(int i=0;i<phones.size();i++){
//            logger.debug("phone={} verification={}",phones.get(i),verification);
//        }
    }
    
    private BasicResult sendVerification(String phones, String verification) {
        String smsUrl = "http://dx.ipyy.net/sms.aspx"; 
        String account = "AA00270";
        String password = "AA0027036";
        String verificationTemplete = "【动吖健康】您的验证码为{0}，请在15分钟内输入。";
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost hp = new HttpPost(smsUrl);
        // 添加发送参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userid", ""));
        params.add(new BasicNameValuePair("account", account));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("mobile", phones));
        params.add(new BasicNameValuePair("content", MessageFormat.format(verificationTemplete, verification)));
        params.add(new BasicNameValuePair("sendTime", "")); // 定时发送时间,为空表示立即发送，定时发送格式2010-10-24
                                                            // 09:08:10
        params.add(new BasicNameValuePair("action", "send")); // 设置为固定的:send
        params.add(new BasicNameValuePair("extno", "")); // 请先询问配置的通道是否支持扩展子号，如果不支持，请填空。子号只能为数字，且最多5位数。
        hp.setEntity(new UrlEncodedFormEntity(params, Charset.forName("utf-8")));
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(hp);
            result = EntityUtils.toString(response.getEntity());
            ByteArrayInputStream in = new ByteArrayInputStream(result.trim().getBytes("UTF-8"));
            Object xmlObject = new DOMParser().parseXML(in);
              JXPathContext ctx = JXPathContext.newContext(xmlObject);
              String returnstatus = (String)ctx.getValue("returnsms/returnstatus");
            if (!"Success".equals(returnstatus)) {
                logger.error("华信语音验证码（{}）发送（{}）:短信服务商返回发送失败 --> {}", verification, phones, result);
                return new BasicResult(1,result);
            }
        } catch (Exception e) {
            logger.error("华信验证码（{}）发送（{}）:发送短信失败", verification, phones, e);
            return new BasicResult(2,result);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("华信验证码发送:关闭响应流失败", e);
                return new BasicResult(3,result);
            }
        }
        return new BasicResult(0,result);
    }
    
    @Override
    public BasicResult sendVoiceVerification(String phone,String verification){
        String voiceUrl = "http://111.206.219.17/c-pt/pt/interface.php";
        String username = "yAA0064";
        String password = "006466";
        String callid =  "01083320219";
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 添加发送参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("action", "captcha"));
        params.add(new BasicNameValuePair("called", phone));
        params.add(new BasicNameValuePair("callid", callid));
        params.add(new BasicNameValuePair("captcha", verification));
        CloseableHttpResponse response = null;
        try {
            URIBuilder uri = new URIBuilder(voiceUrl);
            if (params != null) {
                uri.setParameters(params);
            }
            HttpGet hg = new HttpGet(uri.build().toString());
            response = httpClient.execute(hg);
            result = EntityUtils.toString(response.getEntity());
            ByteArrayInputStream in = new ByteArrayInputStream(result.trim().getBytes("UTF-8"));
            Object xmlObject = new DOMParser().parseXML(in);
              JXPathContext ctx = JXPathContext.newContext(xmlObject);
              String returnstatus = (String)ctx.getValue("data/code");
            if (!"1".equals(returnstatus)) {
                logger.error("华信语音验证码（{}）发送（{}）:短信服务商返回发送失败 --> {}", verification, phone, result);
                return new BasicResult(1,result);
            }
        } catch (Exception e) {
            logger.error("华信语音验证码（{}）发送（{}）:发送短信失败", verification, phone, e);
            return new BasicResult(2,result);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("华信语音验证码发送:关闭响应流失败", e);
                return new BasicResult(3,result);
            }
        }
        return new BasicResult(0,result);
    }
}