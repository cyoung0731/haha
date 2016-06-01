
package com.cy.test.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cy.test.service.HuaxinService;

@Service("deviceService")
public class HuaxinServiceImpl implements HuaxinService {
    private Logger logger = LogManager.getLogger(HuaxinServiceImpl.class);
    @Override
    public void SmsSend() {
        // TODO Auto-generated method stub
        logger.debug("smsservice");
    }
    
}