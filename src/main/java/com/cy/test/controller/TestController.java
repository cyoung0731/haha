
package com.cy.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private Logger logger = LogManager.getLogger(TestController.class);

	// @Autowired
	// private DeviceService deviceService;

	/**
	 * 获取设备列表
	 * 
	 * @param userId
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/test/", method = RequestMethod.GET)
	public void getDeviceList(@RequestParam(value = "username", defaultValue = "-1") String username,
			@RequestParam(value = "passwd", defaultValue = "-1") String passwd) {
		logger.debug("-----hahahaha-----");
		logger.debug("---username={}",username);
		logger.debug("---passwd={}",passwd);
	}
}
