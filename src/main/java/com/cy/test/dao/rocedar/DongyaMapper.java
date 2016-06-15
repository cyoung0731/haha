package com.cy.test.dao.rocedar;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.test.bean.MqBean;

public interface DongyaMapper {
    public String selectUseridByPhone(@Param("phone") String phone);
    
    public List<MqBean> selectMqBean(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
