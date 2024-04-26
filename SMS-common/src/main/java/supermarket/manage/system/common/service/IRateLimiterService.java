package supermarket.manage.system.common.service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/18
 * @Copyright： https://github.com/DukeLewis
 */
public interface IRateLimiterService {

    /**
     * 判断是否允许访问
     * @param key
     * @param limit
     * @param windowSize
     * @return
     */
    boolean isAllowed(String key, long limit, int windowSize);
}
