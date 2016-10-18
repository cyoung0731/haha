package com.cy.test.dao.huanyi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FangzhouMapper {
    public List<String> selectUseridByPhone(@Param("phone") String phone);
}
