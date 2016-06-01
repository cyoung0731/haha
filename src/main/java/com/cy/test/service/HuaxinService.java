package com.cy.test.service;

import java.util.List;

import com.cy.test.result.BasicResult;

public interface HuaxinService {

    /**
     * 发送短信验证码
     * 
     * @param phones
     * @param verification
     * @return
     */
    BasicResult sendVerification(List<String> phones, String verification);

    /**
     * 发送语音验证码
     * 
     * @param phone
     * @param verification
     * @return
     */
    BasicResult sendVoiceVerification(String phone, String verification);
}