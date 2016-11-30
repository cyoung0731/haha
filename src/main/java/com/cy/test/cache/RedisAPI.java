package com.cy.test.cache;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import com.cy.test.exception.RedisException;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAPI {
	/**
	 * 获取JEDIS连接池
	 * 
	 * @param uri
	 * @param config
	 * @return
	 */
	public static JedisPool getJedisPool(URI uri, JedisPoolConfig config) {
		JedisPool pool = new JedisPool(config, uri);
		return pool;
	}

	/**
	 * 获取redis连接池
	 * 
	 * @param url
	 * @param config
	 * @return
	 * @throws RedisException
	 */
	public static JedisPool getJedisPool(String url, JedisPoolConfig config)
			throws RedisException {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RedisException(500301, e.getMessage());
		}
		return getJedisPool(uri, config);
	}

	/**
	 * 获取redis配置
	 * 
	 * @param configProperties
	 * @return
	 */
	public static JedisPoolConfig getPoolConfig(Properties configProperties) {
		JedisPoolConfig config = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxTotal(Integer.parseInt(configProperties.getProperty("redis.max.active", "100")));
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(Integer.parseInt(configProperties.getProperty("redis.max.idle", "10")));
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWaitMillis(Integer.parseInt(configProperties.getProperty("redis.maxwait", "20000")));
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);
		return config;
	}

}
