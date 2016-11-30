package com.cy.test.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.test.result.BasicResult;
import com.cy.util.CyUtil;

@RestController
public class TestRestController {
	private static Logger logger = LogManager.getLogger(TestRestController.class);

	/**
	 * 调用rest接口
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/test/rest/", method = RequestMethod.POST)
	public BasicResult testRest(@RequestParam(value = "url", defaultValue = "-1") String url,
			@RequestParam(value = "type", defaultValue = "GET") String type,
			@RequestParam(value = "headers", defaultValue = "-1") String headerstr,
			@RequestParam(value = "paramstr", defaultValue = "-1") String paramstr,
			@RequestParam(value = "body", defaultValue = "-1") String body) {

		logger.debug("url:" + url);
		logger.debug("type:" + type);
		logger.debug("params: " + paramstr);
		String response = "";
		List<NameValuePair> params = null;
		if (!"-1".equals(paramstr)) {
			String[] paramArray = paramstr.split("&");
			params = new ArrayList<NameValuePair>();
			for (int i = 0; i < paramArray.length; i++) {
				String param = paramArray[i];
				String key = param.split("=")[0];
				String value = param.split("=")[1];
				params.add(new BasicNameValuePair(key, value));
			}
		}

		Map<String, String> headers = null;
		if (!"-1".equals(headerstr)) {
			headers = new HashMap<String, String>();
		}

		if ("-1".equals(body)) {
			body = null;
		}

		if ("post".equals(type)) {
			try {
				response = CyUtil.httpPost(url, params, headers, body);
				logger.debug("接口返回信息:{}", response);
			} catch (Exception e) {
				logger.error("接口调用异常, \n url=" + url + "\n type=" + type + "\n headerstr=" + headerstr + "\n paramstr="
						+ paramstr + "\n body=" + body, e);
				return new BasicResult(-1, "接口调用失败. ||| " + e.getMessage());
			}
		}

		if ("get".equals(type)) {
			try {
				response = CyUtil.httpGet(url, params, headers);
				logger.debug("接口返回信息:{}", response);
			} catch (Exception e) {
				logger.error("接口调用异常, \n url=" + url + "\n type=" + type + "\n headerstr=" + headerstr + "\n paramstr="
						+ paramstr, e);
				return new BasicResult(-1, "接口调用失败. ||| " + e.getMessage());
			}
		}
		return new BasicResult(0, "接口调用成功. 返回信息:" + response);
	}
}
