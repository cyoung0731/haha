
package com.cy.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.test.service.HuaxinService;

@RestController
public class TestHuaxinController {

	private Logger logger = LogManager.getLogger(TestHuaxinController.class);

    @Autowired
    private HuaxinService huaxinService;

	/**
	 * 获取设备列表
	 * 
	 * @param userId
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/huaxin/sms/", method = RequestMethod.GET)
	public void getDeviceList(@RequestParam(value = "phone", defaultValue = "-1") String phone,
			@RequestParam(value = "verification", defaultValue = "-1") String verification) {
		logger.debug("-----hahahaha-----");
		logger.debug("---phone={}",phone);
		logger.debug("---verification={}",verification);
		huaxinService.SmsSend();
	}
}
