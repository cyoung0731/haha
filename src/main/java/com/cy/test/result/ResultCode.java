
package com.cy.test.result;

import java.util.HashMap;
import java.util.Map;

public class ResultCode {

	private static Map<Integer, String> resultMap = new HashMap<Integer, String>();

	static {
		resultMap.put(0, "成功");
		/*
		 * 首位4的错误码是请求类错误：6位数字，第2位为错误类型，3～4位为模块，5～6位为错误序号 1. 第2位标示错误类型 40XXXX 不合法 41XXXX 缺少 42XXXX 超时，过期 43XXXX 需要 44XXXX
		 * 为空 45XXXX 超过限制 46XXXX 不存在 47XXXX 内容错误 48XXXX 权限
		 * 
		 * 2. 3～4位为模块
		 * 
		 * 3. 5～6位为错误序号，从01开始计数
		 */
		// 4X01XX 用户模块
		resultMap.put(400101, "非法请求");
		resultMap.put(400102, "非法手机号");
		resultMap.put(400103, "请输入正确的验证码");
		resultMap.put(400104, "非法openID");
		resultMap.put(440101, "登录异常");
		resultMap.put(450101, "手机号已被注册");
		resultMap.put(450102, "微信帐号已被使用");
		resultMap.put(450103, "帐号在其他设备上登录");
		resultMap.put(450104, "您已添加了");
		resultMap.put(450105, "该手机号码已被绑定");
		resultMap.put(460101, "用户不存在");
		resultMap.put(460102, "您填写的邀请码不存在");
		resultMap.put(470101, "请输入正确的验证码");
		resultMap.put(470102, "不能是自己的邀请码");
		resultMap.put(470103, "邀请码只能填写一次");
		resultMap.put(470104, "不能是自己的手机号");

		// 4X02XX 设备模块
		resultMap.put(460201, "设备不存在");
		resultMap.put(490201, "绑定设备失败");
		resultMap.put(490202, "设备已经绑定");
		resultMap.put(490203, "获取设备列表失败");
		resultMap.put(490204, "设备在用不能解绑");
		resultMap.put(490205, "设备解绑失败");

		// 4X03XX 任务模块
		resultMap.put(440301, "未找到目标");
		resultMap.put(440302, "参数不能为空");
		resultMap.put(440303, "未找到任务");
		resultMap.put(440304, "未找到数据");
		resultMap.put(450301, "超出限制");
		resultMap.put(400301, "参数格式不正确");

		// 4X04XX 积分模块
		resultMap.put(440401, "金币奖励用户为空");
		resultMap.put(450401, "操作次数已达上限");
		resultMap.put(450402, "获取金币已达上限");
		resultMap.put(460401, "金币操作项不存在");
		resultMap.put(460402, "金币操作记录不存在");
		resultMap.put(460403, "金币操作项类型不存在");

		// 4X05XX 活动模块
		resultMap.put(410501, "却少前置条件");
		resultMap.put(420501, "活动已开始");
		resultMap.put(440501, "竞拍活动未开始");
		resultMap.put(440502, "竞拍活动已结束");
		resultMap.put(440503, "抽奖活动未开始");
		resultMap.put(440504, "抽奖活动已结束");
		resultMap.put(440505, "不在报名时间内");
		resultMap.put(450501, "已参与活动");
		resultMap.put(450502, "您的金币不足");
		resultMap.put(450503, "出价失败，请稍后重试");
		resultMap.put(450504, "抽奖券数量不足");
		resultMap.put(450505, "抽奖失败，请稍后重试");
		resultMap.put(450506, "已报名");
		resultMap.put(450507, "您已经抽过奖了！");
		resultMap.put(450508, "完成健康任务后才能抽奖哦！");
		resultMap.put(450509, "您的健康任务个数已达到上限！");
		resultMap.put(450510, "您还没有领取健康任务！");
		resultMap.put(460501, "竞拍活动不存在");
		resultMap.put(460502, "抽奖活动不存在");
		resultMap.put(460503, "活动奖品不存在");

		// 4X06XX 通知模块
		resultMap.put(440601, "设备推送信息为空");

		// 4X07XX 我的模块
		resultMap.put(400701, "不支持的系统类型");
		resultMap.put(400702, "不支持的APP版本");
		resultMap.put(440701, "建议内容不能为空");

		// 4X08XX 动态模块
		resultMap.put(400801, "由于您近期发布过不合规内容，系统已自动处理。如有疑问请联系客服。祝您使用愉快!");
		resultMap.put(440801, "请在圈子内发布");
		resultMap.put(440802, "动态内容不能为空");
		resultMap.put(440803, "动态ID为空");
		resultMap.put(440804, "评论内容不能为空");
		resultMap.put(440805, "动态发布者不能为空");
		resultMap.put(450801, "已点赞");
		resultMap.put(460801, "动态已被删除");
		resultMap.put(480801, "只能删除自己的动态");
		resultMap.put(480802, "只能删除自己的评论");

		// 4X09XX 健康模块
		resultMap.put(440901, "请购买相关服务");
		resultMap.put(440902, "购买信息不全，请联系客服");
		resultMap.put(450901, "不支持的指标类型");
		resultMap.put(470901, "错误的指标数据");
		resultMap.put(480901, "请添加家人信息进行查看");

		// 4X10XX 商城模块
		resultMap.put(441001, "未找到商品");
		resultMap.put(451001, "金币不足");
		resultMap.put(451002, "商品不足");
		resultMap.put(451003, "不到兑换时间");

		// 4X11XX 后台管理模块
		resultMap.put(441101, "该状态的竞拍活动不可关闭");
		resultMap.put(451101, "请先关闭该活动");
		resultMap.put(471101, "密码不正确");
		resultMap.put(481101, "每个活动只能有一个默认奖品");
		resultMap.put(491101, "通知内容不能为空");

		/*
		 * 首位5的错误码是系统类错误：6位数字，第2位预留，3～4位为系统，5～6位为错误序号 1. 第2位预留，全取0
		 * 
		 * 2. 3～4位为模块
		 * 
		 * 3. 5～6位为错误序号 500201 数据库第一种错误
		 */
		// 5001XX 系统
		resultMap.put(500101, "服务器繁忙，请稍后再试");
		resultMap.put(510101, "日期解析错误");
		resultMap.put(520101, "字符串解析错误");

		// 5002XX 数据库
		resultMap.put(500201, "数据库查询错误");
		resultMap.put(510201, "数据库保存错误");
		resultMap.put(520201, "数据库修改错误");
		resultMap.put(530201, "数据库删除错误");

		// 5003XX Redis
		resultMap.put(500301, "Redis查询错误");
		resultMap.put(510301, "Redis保存错误");
		resultMap.put(540301, "Redis取出数据转换错误");

		// 5004XX RabbitMQ
		resultMap.put(500401, "发送消息出错");

		// 5005XX 文件操作错误
		resultMap.put(500501, "读取文件错误");
		resultMap.put(560501, "文件不存在");

		/*
		 * 首位6的错误码为第三方服务类错误：6位数字，第2位预留，3～4位为服务，5～6位为错误序号 1. 第2位预留，全取0
		 * 
		 * 2. 3～4位为具体服务 6003XX 又拍云 6004XX 邮件
		 * 
		 * 3. 5～6位为错误序号 600101 短信服务第一种错误
		 */
		// 6001XX 短信服务
		resultMap.put(600101, "发送短信失败");
		resultMap.put(600102, "短信服务商返回发送失败");
		resultMap.put(600103, "短信验证码类型不支持");

		// 6002XX JPush
		resultMap.put(600201, "JPush推送出错");
		resultMap.put(600202, "JPush定时推送出错");

		// 6003XX 流量
		resultMap.put(600301, "流量查询错误");
		resultMap.put(600302, "流量添加错误");

		// 6004XX 方案
		resultMap.put(600401, "饮食问卷提交错误");
		resultMap.put(600402, "饮食问卷提交错误");

		/*
		 * 首位7的错误码为合作伙伴类错误：6位数字，第2位预留，3～4位为服务，5～6位为错误序号 1. 第2位预留，全取0
		 * 
		 * 2. 首位7的错误码，其中3～4位为具体合作伙伴 7001XX 念加 7002XX fitbit
		 * 
		 * 3. 5～6位为错误序号 700101 念加第一种错误
		 */

		resultMap.put(700000, "调用第三方接口出错");
		resultMap.put(700001, "第三方调用上传数据接口出错");
		resultMap.put(700002, "sn码设备，两个角色绑定失败");
		resultMap.put(700003, "sn码设备，一个角色绑定失败，一个成功");
		// fitbit
		resultMap.put(700201, "调用fitbit接口获取token出错");
		resultMap.put(700202, "调用fitbit接口获取运动数据出错");
		resultMap.put(700203, "调用fitbit接口刷新token出错");

		// 小米
		resultMap.put(700301, "调用小米接口获取用户运动、睡眠数据出错");
		resultMap.put(700302, "调用小米接口获取token出错");
		resultMap.put(700303, "调用小米接口刷新token出错");

		// 乐心
		resultMap.put(700401, "调用乐心接口获取token出错");
		resultMap.put(700402, "调用乐心接口获取数据出错");
		resultMap.put(700403, "调用乐心接口刷新token出错");
		resultMap.put(700404, "调用乐心接口生成签名出错");
		resultMap.put(700405, "调用乐心接口获取code出错");
		// 乐心sn
		resultMap.put(700406, "调用乐心sn查询设备信息接口失败");
		resultMap.put(700407, "调用乐心sn绑定设备接口失败");
		resultMap.put(700408, "绑定失败");
		resultMap.put(700409, "调用乐心sn解绑接口失败");
		resultMap.put(700410, "解绑失败");
		// 悦动圈
		resultMap.put(700602, "调用悦动圈接口获取数据出错");

		// 咕咚
		resultMap.put(700701, "调用咕咚接口获取token出错");
		resultMap.put(700702, "调用咕咚接口获取数据出错");
		resultMap.put(700703, "调用咕咚接口获取用户上传线路出错");
		resultMap.put(700704, "咕咚调用上传接口保存数据失败");

		// javbone
		resultMap.put(700901, "调用jawbone接口获取token出错");
		resultMap.put(700902, "调用jawbone接口获取运动数据出错");
		resultMap.put(700903, "调用jawbone接口刷新运动数据出错");

		// 骑记
		resultMap.put(701001, "调用骑记接口获取token出错");
		resultMap.put(701002, "调用骑记接口获取运动数据出错");
		resultMap.put(701003, "调用骑记接口刷新token出错");

		// 琥蜂一体机
		resultMap.put(700501, "琥蜂数据无体检报告匹配");

		// 康为血糖
		resultMap.put(700801, "康为血糖设备没有被绑定");
		resultMap.put(700802, "康为血糖设备绑定用户验证错误");

		// 好人生电话医生
		resultMap.put(701601, "好人接口返回失败信息");

		// Heha
		resultMap.put(701701, "调用Heha接口获取token出错");
		resultMap.put(701702, "调用Heha接口获取数据出错");

		// 37血压sim卡
		resultMap.put(701201, "设备类型不支持");
		resultMap.put(701202, "注册37健康失败");
		resultMap.put(701203, "绑定设备失败");

		// 糖护士-杏-sn-sim卡
		resultMap.put(701801, "调用糖护士-杏 绑定接口失败");
	}

	/**
	 * 根据状态获取信息
	 * 
	 * @param status
	 */
	public static String getMsg(int status) {
		return resultMap.get(status);
	}
}
