package com.cy.test.controller.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.cy.util.CyUtil;
import com.cy.util.MessageUtils;

public class Test {
	public static final String LEMIAN_USERID = "10651";
	public static final String LEMIAN_USERNAME = "bjgb";
	public static final String LEMIAN_PASSWORD = "Guibai2016";

	public static void main(String[] args) {
//		 lemianFlowOrder(); //乐免订购接口
//		 selectOrderOne(); //乐免订购结果查询方式一
		 selectOrderTwo(); //乐免订购结果查询方式二
//		selectOrderTwoBack(); //乐免订购结果查询方式二回调方法
		
//		String phone = "17011286597";
//		String operator = getOperator(phone); //手机号获取运营商信息
//		System.out.println("------------"+phone+"："+operator);
	}

	// 乐免流量订购
	public static void lemianFlowOrder() {
		String url = "http://sdk.le-mian.com/JsonApi.ashx";
		String UserId = LEMIAN_USERID;
		String UserName = LEMIAN_USERNAME;
		String stamp = CyUtil.formatNowtime("MMddHHmmss");
		String Password = LEMIAN_PASSWORD;
		// 密码加密：用户密码明码+时间戳，然后进行Md5加密，
		// 例如：用户密码为1，时间戳为0113133256，那加密前为：10113133256，
		// 加密后为90569C1E7A03B3E63A9F7D702FE3ECF8
		Password = MessageUtils.toMD5Hex(Password + stamp).toLowerCase();
		String mobile = "13492345678";
		String flow = "5";
		String secret = getLemianSecret(UserId, UserName, Password, mobile, flow, stamp);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("UserId", UserId));
		params.add(new BasicNameValuePair("UserName", UserName));
		params.add(new BasicNameValuePair("Password", Password));
		params.add(new BasicNameValuePair("mobile", mobile));
		params.add(new BasicNameValuePair("flow", flow));
		params.add(new BasicNameValuePair("stamp", stamp));
		params.add(new BasicNameValuePair("secret", secret));
		try {
			String response = CyUtil.httpGet(url, params, null);
			System.out.println("樂免订购接口返回：" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 乐免流量订购签名参数
	public static String getLemianSecret(String UserId, String UserName, String Password, String mobile, String flow,
			String stamp) {
		String str = String.join(",", UserId, UserName, Password, mobile, flow, stamp);
		System.out.println("-----加密前：" + str);
		str = MessageUtils.toMD5Hex(str).toUpperCase();
		return str;
	}

	/**
	 * 乐免流量订购结果查询接口：方式一、订购结果查询接口（结果只返回一次）
	 * 
	 * 接口协议：HTTP提交方式：Get 描述：通过调用该接口，获得用户订购信息明细列表。 注意：该方法只能对订单查询一次，状态返回后订单将提
	 * 升保密级别，查询过的订单不可再次查询。
	 */
	public static void selectOrderOne() {
		String url = "http://sdk.le-mian.com/JsonStatusApi.ashx";
		String UserId = LEMIAN_USERID;
		String UserName = LEMIAN_USERNAME;
		// 密码是通过标准的MD5（32位大写）进行加密的，
		// 用户名密码参数格式应该是：用户密码明码，然后进行Md5加密，
		// 例如：用户密码为1，那加密前为：1，加密后为C4CA4238A0B923820DCC509A6F75849B
		String Password = LEMIAN_PASSWORD;
		Password = MessageUtils.toMD5Hex(Password).toLowerCase();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("UserId", UserId));
		params.add(new BasicNameValuePair("UserName", UserName));
		params.add(new BasicNameValuePair("Password", Password));
		try {
			String response = CyUtil.httpGet(url, params, null);
			System.out.println("樂免订购查询-方式一接口返回：" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 乐免流量订购结果查询接口：方式一、订购结果查询接口（结果只返回一次）
	 * 
	 * 接口协议：HTTP提交方式：Get 描述：通过调用该接口，获得用户订购信息明细列表。 注意：该方法只能对订单查询一次，状态返回后订单将提
	 * 升保密级别，查询过的订单不可再次查询。
	 */
	public static void selectOrderTwo() {
		String url = "http://sdk.le-mian.com/JsonStatus.ashx";
		String UserId = LEMIAN_USERID;
		String UserName = LEMIAN_USERNAME;
		// 密码是通过标准的MD5（32位大写）进行加密的，
		// 用户名密码参数格式应该是：用户密码明码，然后进行Md5加密，
		// 例如：用户密码为1，那加密前为：1，加密后为C4CA4238A0B923820DCC509A6F75849B
		String Password = LEMIAN_PASSWORD;
		Password = MessageUtils.toMD5Hex(Password).toLowerCase();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("UserId", UserId));
		params.add(new BasicNameValuePair("UserName", UserName));
		params.add(new BasicNameValuePair("Password", Password));
		try {
			String response = CyUtil.httpGet(url, params, null);
			System.out.println("樂免订购查询-方式二接口返回：" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * （二）樂免订购查询-方式二接口-反馈信息处理接口 1.反馈信息处理接口描述 接口协议：HTTP 提交方式：Get
	 * 描述：通过调用该接口，避免用户在下次查询订购结果时出现 重复数据的现象。客户也可以不使用该接口，那样做的结果就是
	 * 每次都会取到以前已经获取的数据。建议在每次请求订购结果后 使用该接口。 注意：本接口不用于订单状态查询。
	 */
	public static void selectOrderTwoBack() {
		String url = "http://sdk.le-mian.com/StatusConfirm.ashx";
		String UserId = LEMIAN_USERID;
		String UserName = LEMIAN_USERNAME;
		String Password = MessageUtils.toMD5Hex(LEMIAN_PASSWORD).toLowerCase();
		String msgid = "1610081709087997";
		String mobile = "18611286597";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("UserId", UserId));
		params.add(new BasicNameValuePair("UserName", UserName));
		params.add(new BasicNameValuePair("Password", Password));
		params.add(new BasicNameValuePair("msgid", msgid));
		params.add(new BasicNameValuePair("mobile", mobile));
		try {
			String response = CyUtil.httpGet(url, params, null);
			System.out.println("樂免订购查询-方式二接口-反馈信息处理接口返回值：" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据手机号获取运营商标示
	 * 
	 */
	public static String getOperator(String phone){
		String yd[] =  {"134","135","136","137","138","139","147","150","151","152","157","158","159","182","183","184","187","188"};
		String lt[] = {"130","131","132","145","155","156","185","186"};
		String dx[] = {"133","153","180","181","189"};
		
		String phonePrefix = phone.substring(0,3);
		if(strInArray(phonePrefix, lt)) return "联通";
		if(strInArray(phonePrefix, yd)) return "移动";
		if(strInArray(phonePrefix, dx)) return "电信";
		return "其他";
	}
	
	public static boolean strInArray(String str, String array[]){
		for(int i = 0;i<array.length;i++){
			if (str.equals(array[i])){
				return true;
			}
		}
		return false;
	}
}
