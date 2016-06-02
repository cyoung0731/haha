package com.cy.test.controller.rocedar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class DeviceController {
    private Logger logger = LogManager.getLogger(DeviceController.class);
    
    /**
     * 根据手机号查询userid
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getUserIdByPhone/", method = RequestMethod.GET)
    public void selectUserIdByPhone(@RequestParam(value = "phone", defaultValue = "-1") String phone) {
        logger.debug("getuserid");
    }
}
