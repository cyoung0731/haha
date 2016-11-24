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

import com.cy.test.result.JsonObjectResult;
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
	public JsonObjectResult testRest(@RequestParam(value = "url", defaultValue = "-1") String url,
			@RequestParam(value = "type", defaultValue = "GET") String type,
			@RequestParam(value = "headers", defaultValue = "-1") String headerstr,
			@RequestParam(value = "paramstr", defaultValue = "-1") String paramstr,
			@RequestParam(value = "entity", defaultValue = "-1") String entity) {

		logger.debug("url:" + url);
		logger.debug("type:" + type);
		logger.debug("params" + paramstr);
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

		if ("-1".equals(entity)) {
			entity = null;
		}

		if ("post".equals(type)) {
			try {
				response = CyUtil.httpPost(url, params, headers, entity);
				logger.debug("接口返回信息:{}", response);
			} catch (Exception e) {
				logger.error("接口调用异常, \n url=" + url + "\n type=" + type + "\n headerstr=" + headerstr + "\n paramstr="
						+ paramstr, e);
				return null;
			}
		}

		return null;
		// String response = "";
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("token", token));
		// params.add(new BasicNameValuePair("task_id", String.valueOf(taskIndicatorId)));
		// Map<String, String> headers = new HashMap<String, String>();
		// headers.put("os", String.valueOf(osType));
		// headers.put("app-version", String.valueOf(3110));
		// try {
		// response = CyUtil.httpGet(url, params, headers);
		// logger.debug("-----response={}", response);
		// JSONObject responseJson = JSONObject.fromObject(response);
		// return responseJson;
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return null;
		// return new JsonObjectResult(0, result);
	}
	//
	// public static void main(String[] args) {
	// getDevices("3d7525ba52fa8ebc324212d8652145e3", 3000, 1);
	// }
	//
	// /**
	// * 动吖内网测试环境-设备列表接口
	// *
	// * @param phone
	// * @return
	// */
	// @RequestMapping(value = "/dongya/getdevices/test22/", method = RequestMethod.GET)
	// public static JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
	// @RequestParam(value = "indicator_id", defaultValue = "-1") int taskIndicatorId,
	// @RequestHeader(value = "os", defaultValue = "-1") int osType) {
	// // String url = "http://192.168.18.25/rest/3.0/device/list/";
	// osType = 1;
	// String url = DeviceConstants.IP_TEST_DY + "/device/list/";
	// String response = "";
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// params.add(new BasicNameValuePair("token", token));
	// params.add(new BasicNameValuePair("task_id", String.valueOf(taskIndicatorId)));
	// Map<String, String> headers = new HashMap<String, String>();
	// headers.put("os", String.valueOf(osType));
	// headers.put("app-version", String.valueOf(3110));
	// try {
	// response = CyUtil.httpGet(url, params, headers);
	// logger.debug("-----response={}", response);
	// JSONObject responseJson = JSONObject.fromObject(response);
	// return responseJson;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// /**
	// * 获取设备数据,记录日志
	// *
	// * @param startDate
	// * @param endDate
	// */
	// @RequestMapping(value = "/dongya/getdevicedatalog/", method = RequestMethod.GET)
	// public void getDeviceDateLog(@RequestParam(value = "startDate", defaultValue = "-1") String startDate,
	// @RequestParam(value = "endDate", defaultValue = "-1") String endDate) {
	// List<MqBean> mqBeanList = dongyaService.getMqParam(startDate, endDate);
	// for (Iterator<MqBean> mqList = mqBeanList.iterator(); mqList.hasNext();) {
	// MqBean mqBean = mqList.next();
	// logger.debug(mqBean.getUserId() + "|" + mqBean.getDeviceId() + "|" + mqBean.getTaskId() + "|"
	// + mqBean.getTargetTypeId() + "|" + mqBean.getStartDate() + "|" + mqBean.getEndDate());
	// String url = "http://192.168.18.25/rest/3.0/device/getdata/";
	// String response = "";
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// params.add(new BasicNameValuePair("deviceId", mqBean.getDeviceId()));
	// params.add(new BasicNameValuePair("userId", mqBean.getUserId()));
	// params.add(new BasicNameValuePair("targetTypeId", mqBean.getTargetTypeId()));
	// params.add(new BasicNameValuePair("startDate", mqBean.getStartDate()));
	// params.add(new BasicNameValuePair("endDate", mqBean.getEndDate()));
	// Map<String, String> headers = new HashMap<String, String>();
	// headers.put("os", "1");
	// try {
	// response = CyUtil.httpGet(url, params, headers);
	// logger.debug("-----response={}", response);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// /**
	// * 获取设备数据
	// *
	// * @param phone
	// * @return
	// */
	// @RequestMapping(value = "/dongya/getdevicedata/", method = RequestMethod.GET)
	// public void mq(@RequestParam(value = "userId", defaultValue = "114576668386620038") long userId,
	// @RequestParam(value = "deviceId", defaultValue = "1206001") int deviceId,
	// @RequestParam(value = "targetTypeId", defaultValue = "1000") int targetTypeId,
	// @RequestParam(value = "targetId", defaultValue = "40000") int targetId,
	// @RequestParam(value = "tasktId", defaultValue = "3000") int tasktId,
	// @RequestParam(value = "startDate", defaultValue = "20160425") int startDate,
	// @RequestParam(value = "endDate", defaultValue = "20160425") int endDate) {
	//
	// String url = DeviceConstants.IP_TEST_DY + "/device/getdata/";
	// String response = "";
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// params.add(new BasicNameValuePair("userId", String.valueOf(userId)));
	// params.add(new BasicNameValuePair("deviceId", String.valueOf(deviceId)));
	// params.add(new BasicNameValuePair("targetTypeId", String.valueOf(targetTypeId)));
	// params.add(new BasicNameValuePair("targetId", String.valueOf(targetId)));
	// params.add(new BasicNameValuePair("tasktId", String.valueOf(tasktId)));
	// params.add(new BasicNameValuePair("startDate", String.valueOf(startDate)));
	// params.add(new BasicNameValuePair("endDate", String.valueOf(endDate)));
	//
	// try {
	// response = CyUtil.httpGet(url, params, null);
	// logger.debug("-----response={}", response);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * 获取oauth2协议code
	// *
	// * @param phone
	// * @return
	// */
	// @RequestMapping(value = "", method = RequestMethod.GET)
	// public void getOauth2code(@RequestParam(value = "deviceId", defaultValue = "1204001") int deviceId) {
	//
	// String url = DeviceConstants.IP_TEST_DY + "/device/oauth2/code/{device_id}/";
	// String response = "";
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// try {
	// response = CyUtil.httpGet(url, params, null);
	// logger.debug("-----response={}", response);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * 颈椎操打卡
	// *
	// * @param phone
	// * @return
	// */
	// @RequestMapping(value = "/dongya/daka/jzc/", method = RequestMethod.POST)
	// public JSONObject getDevices(
	// @RequestParam(value = "token", defaultValue = "24194bbb9287102b839c27b6d6c0dde0") String token) {
	// String url = "http://dongya.rocedar.com/rest/3.0/task/bool/data/";
	// String response = "";
	// List<NameValuePair> params = new ArrayList<NameValuePair>();
	// params.add(new BasicNameValuePair("token", token));
	// params.add(new BasicNameValuePair("target_id", "40023"));
	// params.add(new BasicNameValuePair("task_id", "3007"));
	// try {
	// response = CyUtil.httpPost(url, params, null, null);
	// logger.debug("-----response={}", response);
	// JSONObject responseJson = JSONObject.fromObject(response);
	// return responseJson;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
}
