
package com.cy.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.test.result.BasicResult;
import com.cy.test.service.HuaxinService;

@RestController
public class TestHuaxinController {

    private Logger logger = LogManager.getLogger(TestHuaxinController.class);

    @Autowired
    private HuaxinService huaxinService;

    /**
     * 发送短信
     * 
     * @param phone
     * @param verification
     * @return
     */
    @RequestMapping(value = "/huaxin/sms/", method = RequestMethod.GET)
    public BasicResult sendSms(@RequestParam(value = "phone", defaultValue = "-1") String phone,
            @RequestParam(value = "verification", defaultValue = "-1") String verification) {
        logger.debug("-----sendSms-----");
        logger.debug("---phone={}", phone);
        logger.debug("---verification={}", verification);
        List<String> phoneList = new ArrayList<String>();
        String[] phones = phone.split(",");
        for (int i = 0; i < phones.length; i++) {
            phoneList.add(phones[i]);
        }

        return huaxinService.sendVerification(phoneList, verification);
    }
    
    
    /**
     * 发送语音验证码
     * 
     * @param phone
     * @param verification
     * @return
     */
    @RequestMapping(value = "/huaxin/voice/", method = RequestMethod.GET)
    public BasicResult sendVoice(@RequestParam(value = "phone", defaultValue = "-1") String phone,
            @RequestParam(value = "verification", defaultValue = "-1") String verification) {
        logger.debug("-----sendVoice-----");
        logger.debug("---phone={}", phone);
        logger.debug("---verification={}", verification);
        return huaxinService.sendVoiceVerification(phone, verification);
    }
}
