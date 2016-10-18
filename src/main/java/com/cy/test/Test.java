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
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.cy.util.CyUtil;
import com.cy.util.DeviceUtil;
import com.cy.util.MessageUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		// System.out.println(HRSCreart());
		// System.out.println(HRSQuery());
		shuangjiaService();
	}

	public static void shuangjiaService() {
		JSONObject shuangjiaJson = new JSONObject();
		shuangjiaJson.put("MachineId", "HST821255555");
		shuangjiaJson.put("MacAddr", "00-06-55-55-0A-2B");
		shuangjiaJson.put("UnitNo", "4450585525");
		shuangjiaJson.put("UnitName", "XX医院");
		shuangjiaJson.put("RecordNo", "2125555520150409143202");
		shuangjiaJson.put("MeasureTime", "2015-04-09 14:32:02");
		shuangjiaJson.put("LoginType", "1");
		shuangjiaJson.put("DeviceType", "SK-T8");
		shuangjiaJson.put("UserIcon", "");
		// 用户信息
		JSONObject memberJson = new JSONObject();
		memberJson.put("Name", "陈XX");
		memberJson.put("Mobile", "15811397368");
		memberJson.put("IdCode", "440883198909284257");
		memberJson.put("Age", "25");
		memberJson.put("Sex", "1");
		memberJson.put("Address", "广东省XX市");
		memberJson.put("Birthday", "1989-09-28");
		memberJson.put("Nation", "");
		memberJson.put("StartDate", "");
		memberJson.put("EndDate", "");
		memberJson.put("Department", "");
		memberJson.put("BarCode", "");
		memberJson.put("IcCode", "");
		memberJson.put("SocialCode", "");
		memberJson.put("UserID", "");
		shuangjiaJson.put("Member", memberJson);
		// 身高体重
		JSONObject heightJson = new JSONObject();
		heightJson.put("Height", "180");
		heightJson.put("Weight", "70");
		heightJson.put("BMI", "22");
		heightJson.put("IdealWeight", "74");
		heightJson.put("Result", "2");
		shuangjiaJson.put("Height", heightJson);
		// 人体成分（脂肪）
		JSONObject fatJson = new JSONObject();
		fatJson.put("FatRate", "16");
		fatJson.put("Fat", "26.2");
		fatJson.put("ExceptFat", "49.8");
		fatJson.put("WaterRate", "36.4");
		fatJson.put("Water", "26.8");
		fatJson.put("Minerals", "1.2");
		fatJson.put("Protein", "7.6");
		fatJson.put("Fic", "7.6");
		fatJson.put("Foc", "7.6");
		fatJson.put("Muscle", "7.6");
		fatJson.put("FatAdjust", "7.6");
		fatJson.put("WeightAdjust", "7.6");
		fatJson.put("MuscleAdjust", "7.6");
		fatJson.put("BasicMetabolism", "7.6");
		fatJson.put("Viscera", "7.6");
		fatJson.put("Bmc", "7.6");
		fatJson.put("MuscleRate", "7.6");
		fatJson.put("QuganMuscle", "7.6");
		fatJson.put("QuganFat", "7.6");
		fatJson.put("ZuotuiMuscle", "7.6");
		fatJson.put("ZuobiMuscle", "7.6");
		fatJson.put("YoubiMuscle", "7.6");
		fatJson.put("YoutuiMuscle", "7.6");
		fatJson.put("ZuobiFat", "7.6");
		fatJson.put("ZuotuiFat", "7.6");
		fatJson.put("YoubiFat", "7.6");
		fatJson.put("YoutuiFat", "7.6");
		fatJson.put("Result", "");
		shuangjiaJson.put("Fat", fatJson);

//		String url = "http://hub.ubody.net/device/report_submit";
		String url = "http://hub.ubody.net/device/report_submit";
		try {
//			String resopnse = DeviceUtil.httpPost(url, null, null, shuangjiaJson.toString());
//			System.out.println("双佳健康方案接口返回数据：" + resopnse + "  url : " + url + " entity : " + shuangjiaJson.toString());
			String resopnse = DeviceUtil.httpPost(url, null, null, null);
			System.out.println("双佳健康方案接口返回数据：" + resopnse + "  url : " + url + " entity : " + shuangjiaJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		String url = "http://test.mbesthealth.com/cbsService/api/member/query";
		String secret = "fe6bd85258684bbb8c4b767099c003bc";

		String memberId = "1234567890";
		String memberType = "OPEN"; // OPEN-开通 UN_SUBSCRIBE-退订
		String source = "kuibo"; // 默认来源方公司简称
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

		String url = "http://test.mbesthealth.com/cbsService/api/member/create";

		String secret = "fe6bd85258684bbb8c4b767099c003bc";
		// String memberId = UUID.randomUUID().toString();
		String memberId = "1234567890";
		String memberName = "小明";
		String memberType = "OPEN"; // OPEN-开通 UN_SUBSCRIBE-退订
		String certType = "1"; // 1-身份证 2-其他
		String certNo = "220702198909090009";
		String mobile = "13800138000";
		String sex = "1"; // 0-未知 ҅1-男 2-女
		String source = "kuibo"; // 默认来源方公司简称
		String remark = ""; // 备注
		String sign = MessageUtils.toMD5Hex(memberId + certNo + source + secret);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("memberId", memberId));
		params.add(new BasicNameValuePair("memberName", memberName));
		params.add(new BasicNameValuePair("memberType", memberType));
		params.add(new BasicNameValuePair("certType", certType));
		params.add(new BasicNameValuePair("certNo", certNo));
		params.add(new BasicNameValuePair("mobile", mobile));
		params.add(new BasicNameValuePair("sex", sex));
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

		getLexinHeartRate();
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
