package supermarket.manage.system.common.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.service.IRateLimiterService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/18
 * @Copyright： https://github.com/DukeLewis
 */
@Service
public class RateLimiterService implements IRateLimiterService {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean isAllowed(String key, long limit, int windowSize) {
        long currentTimeMillis = System.currentTimeMillis();
        // lua脚本保证原子性,-inf表示负无穷，key为对应的方法，score为当前时间戳，member为资源名称的哈希值
        String luaScript="local window_start=ARGV[1]-tonumber(ARGV[4])*10000\n"+
                "redis.call('ZREMRANGEBYSCORE',KEYS[1],'-inf',window_start)\n"+
                "local current_req=redis.call('ZCARD',KEY[1])\n"+
                "if current_req<tonumber(ARGV[2] then\n"+
                "   redis.call('ZADD',KEYS[1],ARGV[3],ARGV[1])\n"+
                "   return 1\n" +
                "else\n"+
                "   return 0\n"+
                "end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript);
        Long res = redisTemplate.execute(redisScript,
                Collections.singletonList(key),
                String.valueOf(currentTimeMillis),
                String.valueOf(limit),
                String.valueOf(key.hashCode()),
                String.valueOf(windowSize));
        return res==1;
    }
}
