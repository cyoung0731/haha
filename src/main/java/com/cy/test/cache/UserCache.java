
package com.cy.test.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cy.test.exception.CacheException;
import com.cy.test.exception.FileException;
import com.cy.test.exception.RedisException;
import com.cy.util.ConfigUtils;
import com.cy.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class UserCache extends RedisCache {

	private Logger logger = LogManager.getLogger(UserCache.class);

	private static UserCache userCache = new UserCache();

	private UserCache() {
	}

	public static UserCache getUserCache() throws RedisException, FileException {
		if (userCache.getJedisPool() == null) {
			userCache.initJedisPool();
		}
		return userCache;
	}

	/**
	 * 初始化缓冲池
	 */
	@Override
	public void initJedisPool() throws RedisException, FileException {
		Properties redisProperties = ConfigUtils.loadProperties("redis.properties");
		JedisPoolConfig poolConfig = RedisAPI.getPoolConfig(redisProperties);
		JedisPool jedisPool = RedisAPI.getJedisPool(redisProperties.getProperty("redis.uri.user"), poolConfig);
		userCache.setJedisPool(jedisPool);
	}

	/**
	 * 通过token获取用户ID
	 * 
	 * @param token
	 *            登录token
	 * @return 用户ID
	 * @throws RedisException
	 * @throws FileException
	 * @throws CacheException
	 */
	public long getUserId(String token) throws RedisException, FileException, CacheException {
		long userId = 0;
		String uuid = userCache.getString("token:" + token);
		if (StringUtil.isNotNull(uuid)) {
			String[] uuids = uuid.split(":");
			if ("u".equals(uuids[0])) {
				userId = Long.parseLong(uuids[1]);
			} else if ("e".equals(uuids[0])) {
				logger.debug("通过token（token={}）获取的用户ID已在其他设备上登录");
				throw new CacheException(Integer.parseInt(uuids[1]), "用户已在其他设备上登录");
			}
		} else {
			logger.debug("通过token（token={}）未获取到用户ID", token);
			throw new CacheException(440101);
		}
		logger.debug("通过token（token={}）获取用户ID（userId={}）", token, userId);
		return userId;
	}

	/**
	 * 获取用户手机号
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 * @throws FileException
	 */
	public long getUserPhone(long userId) throws RedisException, FileException {
		String phone = userCache.hget("u:" + userId, "phone");
		if (StringUtil.isNotNull(phone)) {
			return Long.parseLong(phone);
		}
		return 0;
	}

	/**
	 * 通过用户ID获取用户基础信息
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 * @throws FileException
	 * @throws CacheException
	 */
	public JSONObject getUserInfoBasis(long userId) throws RedisException {
		JSONObject jsonObject = new JSONObject();
		String[] hkeys = { "sex", "birthday", "height", "weight" };
		List<String> basis = userCache.hmget("u:" + userId, hkeys);
		if (basis != null && basis.size() == 4) {
			jsonObject.put("sex", StringUtil.isNotNull(basis.get(0)) ? Integer.parseInt(basis.get(0)) : -1);
			jsonObject.put("birthday", StringUtil.isNotNull(basis.get(1)) ? Long.parseLong(basis.get(1)) : -1);
			jsonObject.put("height", StringUtil.isNotNull(basis.get(2)) ? Integer.parseInt(basis.get(2)) : -1);
			jsonObject.put("weight", StringUtil.isNotNull(basis.get(3)) ? Integer.parseInt(basis.get(3)) : -1);
		} else {
			jsonObject.put("sex", -1);
			jsonObject.put("birthday", -1);
			jsonObject.put("height", -1);
			jsonObject.put("weight", -1);
		}
		return jsonObject;
	}

	/**
	 * 通过用户ID获取用户圈子信息
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 */
	public JSONObject getUserInfoMessage(long userId) throws RedisException {
		JSONObject jsonObject = new JSONObject();
		String[] hkeys = { "sex", "user_name", "portrait" };
		List<String> healths = userCache.hmget("u:" + userId, hkeys);
		if (healths != null && healths.size() == 3) {
			jsonObject.put("sex", StringUtil.isNotNull(healths.get(0)) ? Integer.parseInt(healths.get(0)) : -1);
			jsonObject.put("user_name", StringUtil.isNotNull(healths.get(1)) ? healths.get(1) : "");
			jsonObject.put("portrait", StringUtil.isNotNull(healths.get(2)) ? healths.get(2) : "");
		} else {
			jsonObject.put("sex", -1);
			jsonObject.put("user_name", "");
			jsonObject.put("portrait", "");
		}
		return jsonObject;
	}

	/**
	 * 通过用户ID获取用户健康信息
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 */
	public JSONObject getUserInfoHealth(long userId) throws RedisException {
		JSONObject jsonObject = new JSONObject();
		String[] hkeys = { "age", "bmi", "physical", "illness" };
		List<String> healths = userCache.hmget("u:" + userId, hkeys);
		if (healths != null && healths.size() == 4) {
			jsonObject.put("age", StringUtil.isNotNull(healths.get(0)) ? Integer.parseInt(healths.get(0)) : -1);
			jsonObject.put("bmi", StringUtil.isNotNull(healths.get(1)) ? Float.parseFloat(healths.get(1)) : 0);
			jsonObject.put("physical", StringUtil.isNotNull(healths.get(2)) ? healths.get(2) : "");
			jsonObject.put("illness", StringUtil.isNotNull(healths.get(3)) ? Integer.parseInt(healths.get(3)) : 0);
		} else {
			jsonObject.put("age", -1);
			jsonObject.put("bmi", 0);
			jsonObject.put("physical", "");
			jsonObject.put("illness", 0);
		}
		return jsonObject;
	}

	/**
	 * 获取用户所有信息
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 * @throws FileException
	 */
	public JSONObject getUserInfo(long userId) throws RedisException, FileException {
		// 从缓存获取用户所有信息
		Map<String, String> cacheUserInfo = userCache.hgetall("u:" + userId);
		if (cacheUserInfo == null || cacheUserInfo.isEmpty()) {
			return new JSONObject();
		}

		// 处理用户信息
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("phone", Long.parseLong(cacheUserInfo.getOrDefault("phone", "-1")));
		jsonObject.put("third_type", Integer.parseInt(cacheUserInfo.getOrDefault("third_type", "2")));
		jsonObject.put("third_open_id", cacheUserInfo.getOrDefault("third_open_id", ""));
		jsonObject.put("third_name", cacheUserInfo.getOrDefault("third_name", ""));
		jsonObject.put("third_sex", Integer.parseInt(cacheUserInfo.getOrDefault("third_sex", "-1")));
		jsonObject.put("third_portrait", cacheUserInfo.getOrDefault("third_portrait", ""));
		jsonObject.put("register_time", Long.parseLong(cacheUserInfo.getOrDefault("register_time", "0")));
		jsonObject.put("true_name", cacheUserInfo.getOrDefault("true_name", ""));
		jsonObject.put("user_name", cacheUserInfo.getOrDefault("user_name", ""));
		jsonObject.put("portrait", cacheUserInfo.getOrDefault("portrait", ""));
		jsonObject.put("sex", Integer.parseInt(cacheUserInfo.getOrDefault("sex", "-1")));
		jsonObject.put("birthday", Integer.parseInt(cacheUserInfo.getOrDefault("birthday", "-1")));
		jsonObject.put("height", Integer.parseInt(cacheUserInfo.getOrDefault("height", "-1")));
		jsonObject.put("weight", Integer.parseInt(cacheUserInfo.getOrDefault("weight", "-1")));
		jsonObject.put("married", Integer.parseInt(cacheUserInfo.getOrDefault("married", "-1")));
		return jsonObject;
	}

	/**
	 * 用户体检报告个数加1
	 *
	 * @param userId
	 * @throws RedisException
	 * @throws FileException
	 */
	public void incrUserAioReport(long userId, int channelId, long reportTime) throws RedisException, FileException {
		// 更新用户一体机最新体检时间
		userCache.hset("aio:report:suggest", String.valueOf(userId), channelId + ":" + reportTime);
		// 增加一体机新报告数
		userCache.hincrby("new:aio:report", String.valueOf(userId), 1);
	}

	/**
	 * 用户体检报告数归0
	 *
	 * @param userId
	 * @throws RedisException
	 * @throws FileException
	 */
	public void zeroUserAioReport(long userId) throws RedisException, FileException {
		userCache.hset("new:aio:report", String.valueOf(userId), "0");
	}

	/**
	 * 获得用户查看消息时间
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 * @throws FileException
	 */
	public long getUserSystemInfoTime(long userId) throws RedisException, FileException {
		String userTime = userCache.hget("new:info:system", String.valueOf(userId));
		if (StringUtil.isNotNull(userTime)) {
			return Long.parseLong(userTime);
		}
		return 0;
	}

	/**
	 * 获取用户一体机体检报告建议
	 *
	 * @param userId
	 * @param dataTime
	 * @return
	 * @throws RedisException
	 * @throws FileException
	 */
	public List<String> getAioSuggest(long userId, long dataTime) throws RedisException, FileException {
		Map<String, String> cachedSuggests = UserCache.getUserCache()
				.hgetall("aio:report:suggest:" + userId + ":" + dataTime);

		List<String> result = new ArrayList<String>();
		if (cachedSuggests != null && cachedSuggests.size() > 0) {
			cachedSuggests = new TreeMap<String, String>(cachedSuggests);
			Collection<String> suggests = cachedSuggests.values();
			for (String suggest : suggests) {
				result.add(suggest);
			}
		}
		return result;
	}

	/**
	 * 查询用户指标信息
	 *
	 * @param userId
	 * @param indicators
	 * @return
	 * @throws FileException
	 * @throws RedisException
	 * @throws HealthException
	 */
	public JSONArray getFamilyIndicators(long userId) throws RedisException, FileException {
		JSONArray indicators = new JSONArray();
		JSONObject bloodSugar = new JSONObject();
		bloodSugar.put("indicator_id", 4016);
		bloodSugar.put("name", "血糖");
		indicators.add(bloodSugar);

		JSONObject bloodPressure = new JSONObject();
		bloodPressure.put("indicator_id", 4012);
		bloodPressure.put("name", "血压");
		indicators.add(bloodPressure);

		JSONObject heartRate = new JSONObject();
		heartRate.put("indicator_id", 4013);
		heartRate.put("name", "心率");
		indicators.add(heartRate);

		JSONArray jsonArray = new JSONArray();
		if (indicators != null && indicators.size() > 0) {
			int indicatorSize = indicators.size();
			String[] hkeys = new String[2 * indicatorSize];

			// 组装hkey
			for (int i = 0; i < indicatorSize; i++) {
				JSONObject indicator = indicators.getJSONObject(i);
				int indicatorId = indicator.optInt("indicator_id", -1);
				hkeys[2 * i] = indicatorId + ":lastest";
				hkeys[2 * i + 1] = indicatorId + ":value";
			}
			List<String> indicatorInfos = UserCache.getUserCache().hmget("indicator:" + userId, hkeys);

			// 封装返回值
			for (int i = 0; i < indicatorSize; i++) {
				String lastest = indicatorInfos.get(2 * i);
				String value = indicatorInfos.get(2 * i + 1);

				JSONObject indicator = indicators.getJSONObject(i);
				indicator.put("time", StringUtil.isNotNull(lastest) ? Long.parseLong(lastest) : -1);
				indicator.put("value", StringUtil.isNotNull(value) ? value : "--");
				jsonArray.add(indicator);
			}
		}
		return jsonArray;
	}

	/**
	 * 根据userId获取用户token
	 *
	 * @param userId
	 * @return
	 * @throws RedisException
	 * @throws FileException
	 */
	public String getTokenByUserId(long userId) throws RedisException, FileException {
		String token = userCache.hget("u:" + userId, "token");
		if (StringUtil.isNotNull(token)) {
			return token;
		} else {
			return "token不存在";
		}
	}

}
