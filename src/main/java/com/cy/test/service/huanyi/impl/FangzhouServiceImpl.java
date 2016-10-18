
package com.cy.test.service.huanyi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.test.dao.huanyi.FangzhouMapper;
import com.cy.test.service.huanyi.FangzhouService;

@Service("fangzhouService")
public class FangzhouServiceImpl implements FangzhouService {
    // private Logger logger = LogManager.getLogger(DongyaServiceImpl.class);

    @Autowired
    public FangzhouMapper fangzhouMapper;

    @Override
    public String getUseridByPhone(String phone) {
        String userId = "";
        List<String> userIdList = fangzhouMapper.selectUseridByPhone(phone);
        for (int i = 0; i < userIdList.size(); i++) {
            if(i == 0){
                userId = userIdList.get(i);
            } else {
                userId = userId + " " + userIdList.get(i);
            }
        }
        return userId;
    }
}