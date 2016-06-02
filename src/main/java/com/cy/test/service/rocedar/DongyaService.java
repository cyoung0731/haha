package com.cy.test.service.rocedar;

public interface DongyaService {

    /**
     * 发送短信验证码
     * 
     * @param phones
     * @param verification
     * @return
     */
   public String getUseridByPhone(String phone);

}