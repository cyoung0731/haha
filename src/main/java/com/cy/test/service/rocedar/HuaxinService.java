package com.cy.test.service.rocedar;

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
    public BasicResult sendVerification(List<String> phones, String verification);

    /**
     * 发送语音验证码
     * 
     * @param phone
     * @param verification
     * @return
     */
    public BasicResult sendVoiceVerification(String phone, String verification);
}