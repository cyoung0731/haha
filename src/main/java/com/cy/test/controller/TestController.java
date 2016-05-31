
package com.cy.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public void getDeviceList(@RequestParam(value = "token", defaultValue = "-1") String token,
			@RequestParam(value = "task_id", defaultValue = "-1") int taskId,
			@RequestHeader(value = "os", defaultValue = "-1") int osType) {
		logger.debug("-----hahahaha-----");
	}
}
