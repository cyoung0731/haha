
package com.cy.test.service.rocedar.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.test.dao.rocedar.DongyaMapper;
import com.cy.test.service.rocedar.DongyaService;

@Service("dongyaService")
public class DongyaServiceImpl implements DongyaService {
    private Logger logger = LogManager.getLogger(DongyaServiceImpl.class);

    @Autowired
    public DongyaMapper dongyaMapper;
    
    @Override
    public String getUseridByPhone(String phone) {
        return dongyaMapper.selectUseridByPhone(phone);
    }
    
}