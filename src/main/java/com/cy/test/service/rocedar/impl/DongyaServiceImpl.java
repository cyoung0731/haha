
package com.cy.test.service.rocedar.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.test.bean.MqBean;
import com.cy.test.dao.rocedar.DongyaMapper;
import com.cy.test.service.rocedar.DongyaService;

@Service("dongyaService")
public class DongyaServiceImpl implements DongyaService {
//    private Logger logger = LogManager.getLogger(DongyaServiceImpl.class);

    @Autowired
    public DongyaMapper dongyaMapper;
    
    @Override
    public String getUseridByPhone(String phone) {
        return dongyaMapper.selectUseridByPhone(phone);
    }
    
    @Override
    public List<MqBean> getMqParam(String startDate, String endDate){
        return dongyaMapper.selectMqBean(startDate, endDate);
    }
    
}