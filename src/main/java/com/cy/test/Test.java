package com.cy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cy.util.CyUtil;
import com.cy.util.DeviceUtil;
import com.cy.util.MessageUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	// test=http://test.mbesthealth.com/
	// online=http://cbsdatainterface.valurise.com/cbsService/
	private static final String VHS_ADDR = "http://cbsdatainterface.valurise.com/cbsService/";
	// test=kuibo
	// online=rocedar
	private static final String VHS_SOURCE = "rocedar";
	// test=fe6bd85258684bbb8c4b767099c003bc
	// online=82552db1e8234a84a4c07841db8349dd
	private static final String VHS_SECRET = "82552db1e8234a84a4c07841db8349dd";

	private static final String LEXIN_APPID = "cda1694c21877e113ca7a1906b0c70fb156f75dc";
	private static final String LEXIN_APPSECRET = "835d3de8f5f561715a506ae4d60ebc11f893f416";

	private static final String THREESEVEN_HTTP_ADDR = "http://openapi.37mhealth.com/openApi.json";
	private static final String THREESEVEN_KEY = "79ecff3fb76cec26e717d5cd08e841a5";
	private static final String THREESEVEN_SECRET = "1bc0934141b890e196b5703bf4d22d2a";

	private static final String DNURSE_HTTP_ADDR = "http://api.ext.dnurse.com/customer/device-activate/";
	private static final String DNURSE_APPID = "10114589";
	private static final String DNURSE_SECRET = "4fd62c3b15fa6782d600b634094e2981";
	private static final String PREFIX = "\\u";

	public static void main(String[] args) {
//		System.out.println("----------");
//		System.out.println(HRSCreart());
//		System.out.println(HRSQuery());
//		System.out.println("++++++++++");
		// lexinBindDevice();
		// lexinUnbindDevice();

		try {
			 getLexinStep();
			// bindDnurseXing("");
			// register37("114611487582158779");
			// bind37("114611487582158779", "F862951024692239", "0000", "000");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getLexinStep() throws Exception {
		String url = "http://open.lifesense.com/openapi_service/business/getSport";
		List<NameValuePair> checksumParams = new ArrayList<NameValuePair>();
		String appId = "6071beb4b1fa1d0e8e7d9cb08f039a1cfae1d365";
		String accessToken = "ea14ef1b4b50f11dd5d17c77a89be3c4b22b8a59";
		String timestamp = String.valueOf(System.currentTimeMillis());
		checksumParams.add(new BasicNameValuePair("app_id", appId));
		checksumParams.add(new BasicNameValuePair("acess_token", accessToken));
		checksumParams.add(new BasicNameValuePair("timestamp", timestamp));
		String appSecret = "db679fc16c9e3af3aaeea70669f02a2d797d047b";
		String checksum = getLexinSign(checksumParams, appSecret);
		url = url + "?app_id=" + appId + "&access_token=" + accessToken + "&timestamp=" + timestamp + "&checksum="
				+ checksum;
		JSONObject entityJson = new JSONObject();
		entityJson.put("openid", "1ea13c52529b67a4f338894e26149f39f7718991");
		entityJson.put("day", "2016-11-29");
		System.out.println("url:\n" + url);
		System.out.println("参数:\n" + entityJson.toString());
		String result = lexinHttpPost(url, null, null, entityJson.toString());
		System.out.println("返回数据：" + result);
	}

	/**
	 * 调用糖护士-杏-绑定接口
	 * 
	 * @param deviceSn
	 *            设备二维码字符串
	 * @return
	 * @throws Exception
	 */
	private static String bindDnurseXing(String deviceSn) throws Exception {
		String logDesc = "调用糖护士绑定接口";
		// deviceSn = URLEncoder.encode("http://we.qq.com/d/AQDYhVy4k7KCHbUSKjZR9CrIj5UGhspNynaKmdER#?xsn=0","UTF-8") ;
		deviceSn = "http://we.qq.com/d/AQDYhVy4k7KCHbUSKjZR9CrIj5UGhspNynaKmdER#?xsn=0";
		String url = DNURSE_HTTP_ADDR + "bind";
		long time = System.currentTimeMillis() / 1000; // 时间戳到秒
		String sign = DNURSE_APPID + deviceSn + time; // 字符串连接
		sign = MessageUtils.toMD5Hex(sign);
		sign = MessageUtils.toMD5Hex(sign + DNURSE_SECRET);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("url", deviceSn));
		params.add(new BasicNameValuePair("pid", DNURSE_APPID));
		params.add(new BasicNameValuePair("sign", sign));
		params.add(new BasicNameValuePair("time", String.valueOf(time)));

		String response = DeviceUtil.httpPost(url, params, null, null);
		System.out.println(logDesc + "请求url：" + url);
		System.out.println(
				logDesc + "请求参数：\n url=" + deviceSn + "\n pid=" + DNURSE_APPID + "\n sign=" + sign + "\n time=" + time);
		System.out.println(logDesc + "返回结果：" + ascii2native(response));
		return response;
	}

	private static String ascii2native(String text) {
		StringBuilder sb = new StringBuilder();
		int start = 0;
		int idx = text.indexOf(PREFIX);
		while (idx != -1) {
			// 上一个 Unicode 码与当前 Unicode 码之间的有效字符
			// eg: \u0101ABC\u0102 之间的ABC
			sb.append(text.substring(start, idx));
			// 转换当前 Unicode 码
			String ascii = text.substring(idx + 2, idx + 6);
			char ch = (char) Integer.parseInt(ascii, 16);
			sb.append(ch);
			// 查找下一个 Unicode
			start = idx + 6;
			idx = text.indexOf(PREFIX, start);
		}
		// 结尾的有效字符
		sb.append(text.substring(start));
		return sb.toString();
	}

	private static String bind37(String appUserId, String deviceSn, String deviceUser, String deviceType)
			throws Exception {
		String logDesc = "调用37绑定接口";
		long time = System.currentTimeMillis() / 1000;
		String method = "bindDevice";
		String sign = MessageUtils.toMD5Hex("secret:" + THREESEVEN_SECRET + ",time:" + time + ",method:" + method);
		JSONObject requestJson = new JSONObject();
		JSONObject systemJson = new JSONObject();
		systemJson.put("key", THREESEVEN_KEY);
		systemJson.put("sign", sign);
		systemJson.put("time", time);
		requestJson.put("system", systemJson);
		requestJson.put("method", method);
		JSONObject paramsJson = new JSONObject();
		paramsJson.put("app_user_id", appUserId);
		paramsJson.put("device_sn", deviceSn);
		paramsJson.put("device_user", deviceUser);
		paramsJson.put("device_type", deviceType);
		requestJson.put("params", paramsJson);
		requestJson.put("id", UUID.randomUUID().toString());
		String response = DeviceUtil.httpPost(THREESEVEN_HTTP_ADDR, null, null, requestJson.toString());
		System.out
				.println(logDesc + "签名参数加密前：" + "secret:" + THREESEVEN_SECRET + ",time:" + time + ",method:" + method);
		System.out.println(logDesc + "请求参数：" + requestJson.toString());
		System.out.println(logDesc + "返回结果：" + response);
		return response;
	}

	private static String register37(String appUserId) throws Exception {
		String logDesc = "调用37注册用户接口";
		long time = System.currentTimeMillis() / 1000;
		String method = "registerUser";
		String sign = MessageUtils.toMD5Hex("secret:" + THREESEVEN_SECRET + ",time:" + time + ",method:" + method);
		JSONObject requestJson = new JSONObject();
		JSONObject systemJson = new JSONObject();
		systemJson.put("key", THREESEVEN_KEY);
		systemJson.put("sign", sign);
		systemJson.put("time", time);
		requestJson.put("system", systemJson);
		requestJson.put("method", method);
		JSONObject paramsJson = new JSONObject();
		paramsJson.put("app_user_id", appUserId);
		requestJson.put("params", paramsJson);
		requestJson.put("id", UUID.randomUUID().toString());
		String response = DeviceUtil.httpPost(THREESEVEN_HTTP_ADDR, null, null, requestJson.toString());
		System.out
				.println(logDesc + "签名参数加密前：" + "secret:" + THREESEVEN_SECRET + ",time:" + time + ",method:" + method);
		System.out.println(logDesc + "请求参数：" + requestJson.toString());
		System.out.println(logDesc + "返回结果：" + response);
		return response;
	}

	public static void lexinUnbindDevice() {
		String url = "http://open.lifesense.com/deviceopenapi_service/device/api/ubindDevice";
		long timestamp = System.currentTimeMillis();
		String nonce = "jeidjgks";
		System.out.println(LEXIN_APPID + LEXIN_APPSECRET + String.valueOf(timestamp) + nonce);
		String checksum = DigestUtils.sha1Hex(LEXIN_APPID + LEXIN_APPSECRET + String.valueOf(timestamp) + nonce);
		// String checksum = getLXSnChecksum(new String[] { LEXIN_APPID,LEXIN_APPSECRET, String.valueOf(timestamp),
		// nonce });
		url = url + "?appid=" + LEXIN_APPID + "&timestamp=" + timestamp + "&nonce=" + nonce + "&checksum=" + checksum;
		System.out.println("url : \n" + url);
		JSONObject entityJson = new JSONObject();
		entityJson.put("deviceId", "530805319563");
		// entityJson.put("mobile", "18611286597");
		entityJson.put("userNo", "1");
		System.out.println("参数 : \n" + entityJson.toString());
		try {
			String response = lexinHttpPost(url, null, null, entityJson.toString());
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void lexinBindDevice() {
		String url = "http://open.lifesense.com/deviceopenapi_service/device/api/bindOperatorDevice";
		// String url = "http://open.lifesense.com/deviceopenapi_service/device/api/bindDevice";
		long timestamp = System.currentTimeMillis();
		String nonce = "jeidjgks";
		System.out.println(LEXIN_APPID + LEXIN_APPSECRET + String.valueOf(timestamp) + nonce);
		String checksum1 = DigestUtils.sha1Hex(LEXIN_APPID + LEXIN_APPSECRET + String.valueOf(timestamp) + nonce);
		System.out.println("乐心签名1=" + checksum1);
		String checksum = lexinSHA1(LEXIN_APPID + LEXIN_APPSECRET + String.valueOf(timestamp) + nonce);
		System.out.println("乐心签名2=" + checksum);
		// String checksum = getLXSnChecksum(new String[] { LEXIN_APPID,LEXIN_APPSECRET, String.valueOf(timestamp),
		// nonce });
		url = url + "?appid=" + LEXIN_APPID + "&timestamp=" + timestamp + "&nonce=" + nonce + "&checksum=" + checksum;
		System.out.println("url : \n" + url);
		JSONObject entityJson = new JSONObject();
		entityJson.put("deviceId", "530805319563");
		// entityJson.put("mobile", "18611286599");
		entityJson.put("userNo", "1");
		System.out.println("参数 : \n" + entityJson.toString());
		try {
			String response = lexinHttpPost(url, null, null, entityJson.toString());
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// APPID b7a2ce498e16d42f4e6f7f0853155b90e5dbc99f
	// APPSECRET 925d2a845d62d7b2f2e9042d306f19aeb9277cbc
	public static void lexinGetDeviceInfo() {
		String url = "http://open.lifesense.com/deviceopenapi_service/device/api/getDeviceinfo";
		long timestamp = System.currentTimeMillis();
		String nonce = "jebk395f";
		String checksum = DigestUtils.sha1Hex(LEXIN_APPID + LEXIN_APPSECRET + String.valueOf(timestamp) + nonce);
		// String checksum = getLXSnChecksum(new String[] { LEXIN_APPID,LEXIN_APPSECRET, String.valueOf(timestamp),
		// nonce });
		url = url + "?appid=" + LEXIN_APPID + "&timestamp=" + timestamp + "&nonce=" + nonce + "&checksum=" + checksum;
		System.out.println(url);
		JSONObject entityJson = new JSONObject();
		entityJson.put("keytype", "sn2");
		entityJson.put("value", "13249507");
		try {
			String response = lexinHttpPost(url, null, null, entityJson.toString());
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * SHA-1加密
	 * 
	 * @return
	 */
	public static String lexinSHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 乐心签名方法
	public static String getLXSnChecksum(String[] contents) {
		// String[] contents = new String[] { "b7a2ce498e16d42f4e6f7f0853155b90e5dbc99f", "refresh_token",
		// "f94cefc883a91c16f068d87089114e8cad98829a", "1474251669775",
		// "925d2a845d62d7b2f2e9042d306f19aeb9277cbc" };
		StringBuilder buff = new StringBuilder();
		Arrays.asList(contents).stream().sorted().forEach(e -> buff.append(e));
		String checksum = DigestUtils.sha1Hex(buff.toString());
		// System.out.println(checksum);
		return checksum;
	}

	/**
	 * 乐心httpPost方法
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private static String lexinHttpPost(String url, List<NameValuePair> params, Map<String, String> headers,
			String entityString) {
		HttpPost hp = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			if (params != null) {
				hp.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					hp.addHeader(key, headers.get(key));
				}
			}
			if (entityString != null) {
				StringEntity entity = new StringEntity(entityString, "utf-8");// 解决中文乱码问题
				entity.setContentType("application/json");
				hp.setEntity(entity);
			}
			CloseableHttpClient httpClient = HttpClients.createDefault();
			response = httpClient.execute(hp);
			return response.toString();
			// System.out.println("response返回"+response.toString());
			// HttpEntity responseEntity = response.getEntity();
			// String reponseString = EntityUtils.toString(responseEntity, "utf-8");
			// return reponseString;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("httpPost请求异常,接口地址:" + url + "参数:" + params + "headers:" + headers.toString() + "entity:"
					+ entityString);
			return null;
			// logger.error("httpPost请求异常,接口地址=" + url, e);
			// throw new DeviceException(700000, "httpPost请求异常,接口地址:" + url + "参数:" + params + "headers:"
			// + headers.toString() + "entity:" + entityString);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				// logger.error("httpPost请求关闭响应流失败,接口地址=" + url, e);
			}
		}
	}

	//////////////////////////////////////////////

	public static void LXsign() {
		String[] contents = new String[] { "b7a2ce498e16d42f4e6f7f0853155b90e5dbc99f", "refresh_token",
				"f94cefc883a91c16f068d87089114e8cad98829a", "1474251669775",
				"925d2a845d62d7b2f2e9042d306f19aeb9277cbc" };
		StringBuilder buff = new StringBuilder();
		Arrays.asList(contents).stream().sorted().forEach(e -> buff.append(e));
		String checksum = DigestUtils.sha1Hex(buff.toString());
		System.out.println(checksum);
		System.out.println(SHA1(
				"1474251669775925d2a845d62d7b2f2e9042d306f19aeb9277cbcb7a2ce498e16d42f4e6f7f0853155b90e5dbc99ff94cefc883a91c16f068d87089114e8cad98829arefresh_token"));

	}

	public static String HRSQuery() {
		String url = VHS_ADDR + "api/member/query";
		String secret = VHS_SECRET;

		String memberId = "test1";
		String memberType = "OPEN"; // OPEN-开通 UN_SUBSCRIBE-退订
		String source = VHS_SOURCE; // 默认来源方公司简称
		String sign = MessageUtils.toMD5Hex(memberId + source + secret);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("memberId", memberId));
		params.add(new BasicNameValuePair("memberType", memberType));
		params.add(new BasicNameValuePair("source", source));
		params.add(new BasicNameValuePair("sign", sign));

		String response = "";
		try {
			response = CyUtil.httpPost(url, params, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String HRSCreart() {
		// source=kuibo
		// secret=fe6bd85258684bbb8c4b767099c003bc
		// http://cbsdatainterface.valurise.com/cbsService/cbsService/api/member/create
		String url = VHS_ADDR + "api/member/create";

		String secret = VHS_SECRET;
		// String memberId = UUID.randomUUID().toString();
		String memberId = "test1";
		String memberName = "王军";
		String memberType = "OPEN"; // OPEN-开通 UN_SUBSCRIBE-退订
		String certType = ""; // 1-身份证 2-其他
		String certNo = "";
		String mobile = "15900098586";
		String sex = ""; // 0-未知 ҅1-男 2-女
		String source = VHS_SOURCE; // 默认来源方公司简称
		String remark = ""; // 备注
		String sign = MessageUtils.toMD5Hex(memberId + certNo + source + secret);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("memberId", memberId));
		 params.add(new BasicNameValuePair("memberName", memberName));
		params.add(new BasicNameValuePair("memberType", memberType));
		// params.add(new BasicNameValuePair("certType", certType));
		 params.add(new BasicNameValuePair("certNo", certNo));
		params.add(new BasicNameValuePair("mobile", mobile));
		// params.add(new BasicNameValuePair("sex", sex));
		params.add(new BasicNameValuePair("source", source));
		 params.add(new BasicNameValuePair("remark", remark));
		params.add(new BasicNameValuePair("sign", sign));

		String response = "";
		try {
			response = CyUtil.httpPost(url, params, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		// String sr =
		// sendPost("http://qa.sports.lifesense.com/openapi_service/oauth2/access_token",
		// "app_id=b7a2ce498e16d42f4e6f7f0853155b90e5dbc99f&grant_type=authorization_code&code=7498203ebb91f5cfe482ae4268780cc9bced3b46&timestamp=1473321252368&checksum=4ad674a8961e885d7ca3b16185f627c5ddc1ed40");

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static JSONArray fromatDeviceResultJson(JSONArray deviceArray, JSONObject mqJson, String valueName,
			String dateName) {
		JSONArray resultArray = new JSONArray();
		int indicatorId = mqJson.getInt("indicatorId");
		for (Object object : deviceArray) {
			JSONObject subJson = JSONObject.fromObject(object);
			// JSONObject resultJson = JSONObject.fromObject(mqJson);
			JSONObject resultJson = new JSONObject();
			resultJson.put("userId", mqJson.getLong("userId"));
			resultJson.put("companyId", mqJson.getInt("companyId"));
			resultJson.put("indicatorId", mqJson.getInt("indicatorId"));
			resultJson.put("deviceId", mqJson.getInt("deviceId"));
			String value = "";
			value = subJson.getString(valueName);
			// 跑步返回值扩展字段（跑步距离；跑步卡路里）
			if (indicatorId == 4001 || indicatorId == 4002) {
				DecimalFormat df = new DecimalFormat("######0.00");
				value = df.format(subJson.getDouble("distance") / 1000);
				resultJson.put("deriveData", (subJson.getInt("sum_cost_time") / 60 + 1) + ";"
						+ df.format(subJson.getDouble("caloric") / 1000));
			} else {
				resultJson.put("deriveData", "");
			}
			resultJson.put("value", value);
			resultJson.put("recordDate", Integer.valueOf(subJson.getString(dateName).replace("-", "").split(" ")[0]));
			// 调用接口数据写入日志文件
			if (mqJson.has("logTag") && mqJson.getInt("logTag") == 1) {
				DeviceUtil.deviceDataLogProcessed(resultJson.getLong("userId"), resultJson.getInt("companyId"),
						resultJson.getInt("deviceId"), resultJson.getInt("indicatorId"), resultJson.getString("value"),
						resultJson.getString("deriveData"), resultJson.getInt("recordDate"), subJson);
			}
			resultArray.add(resultJson);
		}
		return resultArray;
	}

	// 取随机数
	private static void getRandorm() {
		Random random = new Random();
		int a = random.nextInt((9999 - 1000) + 1) + 1;
		System.out.println(a);
	}

	/**
	 * 获取乐心checksum参数
	 * 
	 * SHA1(AppSecret+app_id+scope+state+ response_type +timestamp), 将上述参数按照ASC码先排序，然后拼接成一个字符串，再进行SHA1哈希计算，
	 * 转化成16进制字符(String，小写)
	 * 
	 * 示例： URL： https://openapi.lifesense.com/oauth2/authorize?app_id= Va5yQRHlA4Fq4eR3LT0vuXV4&
	 * scope=sport&state=aydRHl4qeT0vuX4&response_type=code&timestamp= 1030040877777& checksum=hjjjshglueKhf89YKHNM6gsj
	 * 38e99a2fbb76c89e114e3355bc917cf92ec19a7b
	 * 
	 */
	private static void getLexinChecksum() {
		String appSecret = "AppSecret";
		long timestamp = System.currentTimeMillis();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_id", "Va5yQRHlA4Fq4eR3LT0vuXV4"));
		params.add(new BasicNameValuePair("scope", "sport"));
		params.add(new BasicNameValuePair("state", "aydRHl4qeT0vuX4"));
		params.add(new BasicNameValuePair("response_type", "code"));
		params.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
		String checksum = getLexinSign(params, appSecret);
		System.out.println(checksum);
		params.add(new BasicNameValuePair("checksum", checksum));

	}

	// SHA1加密
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 获取乐心接口签名
	private static String getLexinSign(List<NameValuePair> params, String appSecret) {
		List<String> paramValueList = new ArrayList<String>();
		paramValueList.add(appSecret);

		System.out.println("参数:" + params.toString());

		// 参数按照ASC码先排序
		for (NameValuePair param : params) {
			paramValueList.add(param.getValue());
		}
		Collections.sort(paramValueList);
		StringBuffer sb = new StringBuffer();
		// 然后拼接成一个字符串
		for (String s : paramValueList) {
			sb.append(s);
		}

		System.out.println("参数值排序:" + paramValueList.toString());
		System.out.println("参数值排序后拼接:" + sb.toString());
		System.out.println("SHA1加密:" + SHA1(sb.toString()));
		return SHA1(sb.toString());
	}

	// 获取绝世好医url
	private static void getJSHYUrl() {
		/*
		 * 以下是我们绝世好医的H5功能的接入说明。
		 * 
		 * http://mydoctor.valurise.com/web/start-md?cid={绝世好医客户ID}&uid={用户ID}& ts={当前时间戳(毫秒)}&_sign={MD5签名}
		 * 
		 * 贵司的cid是rocedar，密钥是y48s9mr11bifqufd 签名生成策略：cid+uid+密钥+ts
		 * 
		 * 举个例子, uid是 zhangchongyang、ts是 1471589321250 的话： 签名的原字符串应该是：
		 * rocedarzhangchongyangy48s9mr11bifqufd1471589321250 生成的MD5签名应该是：e9340040eca2aa0bca1fe12aee3ff76f
		 * 
		 * 要是集成过程中有遇到任何问题都可以在群里面咨询。
		 */

		String cid = "rocedar";
		String key = "y48s9mr11bifqufd";
		String uid = "123456"; // 改成我们的
		long ts = System.currentTimeMillis();
		String sign = MessageUtils.toMD5Hex(cid + uid + key + ts);
		String url = "http://mydoctor.valurise.com/web/start-md?cid=" + cid + "&uid=" + uid + "&ts=" + ts + "&_sign="
				+ sign;
		System.out.println("--------------" + url);

		String a = "2011-06-16T14:00:00.000";
		System.out.println("--------------" + a.substring(11, 19).replace(":", ""));

		String b = "4b";
		int hah = Integer.parseInt(b, 16);
		System.out.println(hah);

		// getLexinHeartRate();
	}

	// 乐心取心率数据
	private static void getLexinHeartRate() {
		String s = "000000565054545b51505352525b5050504b4c4b4b4b504b5050504d4c504b53494b575146494b4b4b49555c46594b595054464e484b504446484b4943434a48464e454c5550454e494548514e4b424249494749494446464a4450494249414348474147414247475047474748424844475b4c4c57565551515042535352474b53515d47475a505c455e48455d595c5c575f5858464e004843000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		for (int i = 0; i < s.length(); i = i + 2) {
			int heartRate = Integer.parseInt(s.substring(i, i + 2), 16);
			System.out.println(
					heartRate + "|" + String.format("%02d", i / 24) + ":" + String.format("%02d", i / 2 * 5 % 60));
			// System.out.println(
			// heartRate + "|" + String.format("%02d", i / 24) +
			// String.format("%02d", i / 2 * 5 % 60) + "00");
		}
	}

	// 进行SHA1哈希计算
	private static String SHA1_2(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
