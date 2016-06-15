package com.cy.test.service.rocedar;

import java.util.List;

import com.cy.test.bean.MqBean;

public interface DongyaService {

    /**
     * 发送短信验证码
     * 
     * @param phones
     * @param verification
     * @return
     */
   public String getUseridByPhone(String phone);

    /**
     * 获取mq参数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    List<MqBean> getMqParam(String startDate, String endDate);

}