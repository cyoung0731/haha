package com.cy.test.dao.rocedar;

import org.apache.ibatis.annotations.Param;

public interface DongyaMapper {
    public String selectUseridByPhone(@Param("phone") String phone);
}
