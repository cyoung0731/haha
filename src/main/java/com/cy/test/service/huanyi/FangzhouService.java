package com.cy.test.service.huanyi;

public interface FangzhouService {

    /**
     * 发送短信验证码
     * 
     * @param phones
     * @param verification
     * @return
     */
    public String getUseridByPhone(String phone);

}