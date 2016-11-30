
package com.cy.test.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cy.test.exception.FileException;
import com.cy.test.exception.RedisException;
import com.cy.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public abstract class RedisCache {

    private Logger logger = LogManager.getLogger(RedisCache.class);

    private JedisPool jedisPool;

    public abstract void initJedisPool() throws RedisException, FileException;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     * @return
     * @throws RedisException
     */
    public Long expire(final String key, final int seconds) throws RedisException {
        Long isSuccess = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            isSuccess = jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.warn("Warn! Exists [{}] happend error! {}", key, e);
            throw new RedisException(500301, "判断键是否存在出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return isSuccess;
    }

    /**
     * 判断是否存在键
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public boolean exists(final String key) throws RedisException {
        boolean keyExists = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            keyExists = jedis.exists(key);
        } catch (Exception e) {
            logger.warn("Warn! Exists [{}] happend error! {}", key, e);
            throw new RedisException(500301, "判断键是否存在出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keyExists;
    }

    /**
     * 设置字符串
     * 
     * @param key
     *        缓存键名称
     * @param value
     *        缓存值
     * @throws RedisException
     */
    public void set(final String key, final String value) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.warn("Warn! Set key {} error to value {}. {}", key, value, e);
            throw new RedisException(500301, "SET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置字符串及键的有效时常
     * 
     * @param key
     *        缓存键名称
     * @param value
     *        缓存值
     * @param time
     *        有效时长
     * @throws RedisException
     */
    public void set(final String key, final String value, int time) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, time);
        } catch (Exception e) {
            logger.warn("Warn! Set key {} error to value {}. {}", key, value, e);
            throw new RedisException(500301, "SET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 给key加1
     *
     * @param key
     * @return
     * @throws RedisException
     */
    public Long incr(final String key) throws RedisException {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.incr(key);
        } catch (Exception e) {
            logger.warn("Warn! Set key {} error to value {}. {}", key, value, e);
            throw new RedisException(500301, "SET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 给key加integer值
     *
     * @param key
     * @return
     */
    public Long incrBy(final String key, final long integer) throws RedisException {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.incrBy(key, integer);
        } catch (Exception e) {
            logger.warn("Warn! Set key {} error to value {}. {}", key, value, e);
            throw new RedisException(500301, "SET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }
    
    /**
     * 根据模式获取哈希列表值
     *
     * @param key
     * @param cursor
     * @return
     * @throws RedisException
     */ 
    public List<Map.Entry<String, String>> hscan(final String key, final String pattern)throws RedisException{
        List<Map.Entry<String, String>> result = this.hscan(key, pattern, 0);
        return result;
    }
    
    /**
     * 获取hash所有值
     *
     * @param key
     * @return
     * @throws RedisException
     */
    public Map<String, String> hgetall(final String key) throws RedisException{
        Jedis jedis = null;
        Map<String, String> result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            logger.warn("Warn|hgetAll key {}. {}", key, e);
            throw new RedisException(500301, "HGETALL出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }
    
    /**
     * 设置哈希值
     * 
     * @param key
     *        缓存键名称
     * @param hkey
     *        哈希键名称
     * @param value
     *        哈希值
     * @throws RedisException
     */
    public void hset(final String key, final String hkey, final String value) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, hkey, value);
        } catch (Exception e) {
            logger.warn("Warn! HSet key {}  hkey {} error to value {}. {}", key, hkey, value, e);
            throw new RedisException(500301, "HSET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 删除哈希键
     *
     * @param key
     *        缓存键名称
     * @param hkey
     *        哈希键名称
     * @throws RedisException
     */
    public void hdel(final String key, final String... hkey) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(key, hkey);
        } catch (Exception e) {
            logger.warn("Warn! HDEL key {}  hkey {} error.", key, hkey);
            throw new RedisException(500301, "HDEL出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 为哈希表key中的域hkey的值加上增量value
     * 
     * @param key
     *        缓存键名称
     * @param hkey
     *        哈希键名称
     * @param value
     *        增量
     * @throws RedisException
     */
    public void hincrby(final String key, final String hkey, final int value) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hincrBy(key, hkey, value);
        } catch (Exception e) {
            logger.warn("Warn! hincrby key {}  hkey {} error to value {}. {}", key, hkey, value, e);
            throw new RedisException(500301, "HINCRBY出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置多个哈希值
     * 
     * @param key
     * @param hashValue
     * @throws RedisException
     */
    public void hmset(final String key, final Map<String, String> hashValue) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key, hashValue);
        } catch (Exception e) {
            logger.warn("Warn! hmset key {} error to value {}. {}", key, hashValue, e);
            throw new RedisException(500301, "HMSET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从右加入LIST值
     * 
     * @param key
     * @param values
     * @throws RedisException
     */
    public void rpush(final String key, final String... values) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.rpush(key, values);
        } catch (Exception e) {
            logger.warn("Warn! rpush key {} error to value {}. {}", key, values, e);
            throw new RedisException(500301, "RPUSH出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从左加入LIST值
     * 
     * @param key
     * @param values
     * @throws RedisException
     */
    public void lpush(final String key, final String... values) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, values);
        } catch (Exception e) {
            logger.warn("Warn! rpush key {} error to value {}. {}", key, values, e);
            throw new RedisException(500301, "LPUSH出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 添加集合数据
     * 
     * @param key
     * @param members
     * @throws RedisException
     */
    public void sadd(String key, String... members) throws RedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.sadd(key, members);
        } catch (Exception e) {
            logger.warn("Warn! sadd key {} error to value {}. {}", key, members, e);
            throw new RedisException(500301, "sadd出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取字符串
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public String getString(final String key) throws RedisException {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.warn("Warn! Get key {} error!. {}", key, e);
            throw new RedisException(500301, "GET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取整数
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public Integer getInt(final String key) throws RedisException {
        Jedis jedis = null;
        Integer value = null;
        try {
            jedis = jedisPool.getResource();
            String valueStr = jedis.get(key);

            if (!StringUtil.isEmpty(valueStr)) {
                value = Integer.parseInt(valueStr);
            }
        } catch (NumberFormatException e) {
            logger.warn("Warn! Get key {}, format to integer error!.", key);
            logger.warn("字符串转换出错", e);
            throw new RedisException(540301, "字符串转换出错");
        } catch (Exception e) {
            logger.warn("Warn! Get key {} error!. {}", key, e);
            throw new RedisException(500301, "GET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取JSON对象
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public JSONObject getJsonObject(final String key) throws RedisException {
        Jedis jedis = null;
        JSONObject json = new JSONObject();
        try {
            jedis = jedisPool.getResource();
            String valueStr = jedis.get(key);
            json = JSONObject.fromObject(valueStr);
        } catch (Exception e) {
            logger.warn("Warn! Get key {} error!. {}", key, e);
            throw new RedisException(500301, "GET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return json;
    }

    /**
     * 获取JSON数组
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public JSONArray getJsonArray(final String key) throws RedisException {
        Jedis jedis = null;
        JSONArray json = new JSONArray();
        try {
            jedis = jedisPool.getResource();
            String valueStr = jedis.get(key);
            json = JSONArray.fromObject(valueStr);
        } catch (Exception e) {
            logger.warn("Warn! Get key {} error!. {}", key, e);
            throw new RedisException(500301, "GET出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return json;
    }

    /**
     * 获取队列长度
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public Long llen(final String key) throws RedisException {
        Jedis jedis = null;
        Long value = (long) 0;
        try {
            jedis = jedisPool.getResource();
            value = jedis.llen(key);
        } catch (Exception e) {
            logger.warn("Warn! llen key {} error!. {}", key, e);
            throw new RedisException(500301, "llen出错!");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取队列list的一段值
     * 
     * @param key
     * @param start
     * @param end
     * @return
     * @throws RedisException
     */
    public List<String> lrange(final String key, final long start, final long end) throws RedisException {
        Jedis jedis = null;
        List<String> value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.lrange(key, start, end);
        } catch (Exception e) {
            logger.warn("Warn! lrange key {} satrt {} end {} error!. {}", key, start, end, e);
            throw new RedisException(500301, "lrange出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 左弹出数据
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public String lpop(final String key) throws RedisException {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.lpop(key);
        } catch (Exception e) {
            logger.warn("Warn! lpop key {} error!. {}", key, e);
            throw new RedisException(500301, "lpop出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 根据参数count的值，移除列表中与参数value相等的元素
     * 
     * @param key
     * @param count
     * @param value
     * @return
     * @throws RedisException
     */
    public Long lrem(final String key, final long count, final String value) throws RedisException {
        Jedis jedis = null;
        Long num = 0l;
        try {
            jedis = jedisPool.getResource();
            num = jedis.lrem(key, count, value);
        } catch (Exception e) {
            logger.warn("Warn! lrem key {} count {} value {} error!. {}", key, value, e);
            throw new RedisException(500301, "lrem出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return num;
    }

    /**
     * 右弹出数据
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public String rpop(final String key) throws RedisException {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.rpop(key);
        } catch (Exception e) {
            logger.warn("Warn! rpop key {} error!. {}", key, e);
            throw new RedisException(500301, "rpop出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取hash单个数据
     * 
     * @param key
     * @param hkey
     * @return
     * @throws RedisException
     */
    public String hget(final String key, final String hkey) throws RedisException {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, hkey);
        } catch (Exception e) {
            logger.warn("Warn! hget key {} hkey {} error!. {}", key, hkey, e);
            throw new RedisException(500301, "hget出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取hash多个数据
     * 
     * @param key
     * @param hkeys
     * @return
     * @throws RedisException
     */
    public List<String> hmget(final String key, final String... hkeys) throws RedisException {
        Jedis jedis = null;
        List<String> value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hmget(key, hkeys);
        } catch (Exception e) {
            logger.warn("Warn! hmget key {} hkeys {} error!. {}", key, hkeys, e);
            throw new RedisException(500301, "hmget出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取hash所有值
     *
     * @param key
     * @return
     * @throws RedisException
     */
    public List<String> hvals(final String key) throws RedisException {
        Jedis jedis = null;
        List<String> value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hvals(key);
        } catch (Exception e) {
            logger.warn("Warn! hvals key {} error!. {}", key, e);
            throw new RedisException(500301, "hvals出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 迭代哈希键中的键值对
     *
     * @param key
     * @param pattern
     *        哈希键的正则
     * @param count
     * @return
     * @throws RedisException
     */
    public List<Entry<String, String>> hscan(final String key, String pattern, int count) throws RedisException {
        Jedis jedis = null;
        List<Entry<String, String>> value = new ArrayList<Map.Entry<String,String>>();
        try {
            int cursor = 0;
            jedis = jedisPool.getResource();
            ScanParams params = new ScanParams();
            params.match(pattern);
            if (count > 0) {
                params.count(count);
            }
            ScanResult<Map.Entry<String, String>> scanResult;
            do {
                scanResult = jedis.hscan(key, String.valueOf(cursor), params);
                value.addAll(scanResult.getResult());
                cursor = Integer.parseInt(scanResult.getStringCursor());
            } while (cursor > 0);
        } catch (Exception e) {
            logger.warn("Warn! hvals key {} error!. {}", key, e);
            throw new RedisException(500301, "hvals出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 判断hash里面的键是否存在
     * 
     * @param key
     * @param hkey
     * @return
     * @throws RedisException
     */
    public boolean hexists(final String key, final String hkey) throws RedisException {
        Jedis jedis = null;
        boolean value = false;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hexists(key, hkey);
        } catch (Exception e) {
            logger.warn("Warn! hexists key {} hkeys {} error!. {}", key, hkey, e);
            throw new RedisException(500301, "hexists出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 随机取集合中的数，并删除
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public String spop(final String key) throws RedisException {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.spop(key);
        } catch (Exception e) {
            logger.warn("Warn! spop key {}error!. {}", key, e);
            throw new RedisException(500301, "spop出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 随机取集合中的
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public List<String> srandmember(final String key, final int count) throws RedisException {
        Jedis jedis = null;
        List<String> value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.srandmember(key, count);
        } catch (Exception e) {
            logger.warn("Warn! spop key {}error!. {}", key, e);
            throw new RedisException(500301, "spop出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }
    
    /**
     * 获取集合数据个数
     *
     * @param key
     * @return
     * @throws RedisException
     */
    public Long scard(final String key) throws RedisException {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.scard(key);
        } catch (Exception e) {
            logger.warn("Warn! scard key {} error!. {}", key, e);
            throw new RedisException(500301, "scard出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 删除字符串
     * 
     * @param key
     * @return
     * @throws RedisException
     */
    public Long del(final String key) throws RedisException {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.del(key);
        } catch (Exception e) {
            logger.warn("Warn! Del key {} error!. {}", key, e);
            throw new RedisException(500301, "DEL出错");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

}
