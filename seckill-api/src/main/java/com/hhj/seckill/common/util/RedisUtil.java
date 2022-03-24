package com.hhj.seckill.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ValueOperations<String, Object> valueOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;


    private final String LUA="if redis.call(\"get\",KEYS[1] )> ARGV[1] then\n" +
            "\treturn redis.call(\"decr\",KEYS[1])\n" +
            "else\n" +
            "\treturn 0\n" +
            "end";

    
    public static final long NOT_EXPIRE = -1;

    public long luaStock(String key){
        DefaultRedisScript<Long> longDefaultRedisScript = new DefaultRedisScript<>(LUA,Long.class);
        Long execute = redisTemplate.execute(longDefaultRedisScript, Collections.singletonList(key),0);
        return (Long)execute;
//        System.out.println(execute);
    }

    
    public void  set(String key, Object value, long expire) {
        // 若是字符串则直接存储；若是对象先转化为json字符串再存储
        valueOperations.set(key,value);


        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value, long expire,TimeUnit unit) {
        // 若是字符串则直接存储；若是对象先转化为json字符串再存储
        valueOperations.set(key,value);


        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, unit);
        }
    }

    
    public Long getExire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    
    public boolean setnx(String key, Object value, long expire) {
        // 若是字符串则直接存储；若是对象先转化为json字符串再存储
        Boolean aBoolean = valueOperations.setIfAbsent(key, value);

        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.DAYS);
        }
        return Boolean.TRUE.equals(aBoolean);
    }

    
    public <T> T getObj(String key, Class<T> clazz, long expire) {
        Object o = valueOperations.get(key);
        String s = JSONObject.toJSONString(o);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        // 若获取的是字符串，直接返回即可；若获取的是对象，则将json字符串转化为对象再返回
        return clazz == String.class ? (T) s : JsonUtil.toObj(s, clazz);
    }
    
    public <T> T getObj(String key, Class<T> clazz) {
        return getObj(key,clazz,NOT_EXPIRE);
    }

    
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Long decr(String key){
        Long decrement = valueOperations.decrement(key);
        return decrement;
    }

    public Long incr(String key){
        Long increment = valueOperations.increment(key);
        return increment;
    }

    
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =================================Map==============================
    
    public Object hget(String key, String item) {
        return hashOperations.get(key, item);
    }

    
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);

    }

    
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================

    
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================

    
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
