package supermarket.manage.system.config;

import com.alibaba.fastjson2.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import supermarket.manage.system.autoconfig.property.ThreadPoolProperty;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池配置类
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {
    @Bean("inventoryThreadPool")
    public ExecutorService inventoryThreadPool(ThreadPoolProperty threadPoolProperty){
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setNameFormat("inventory-thread-pool-%d");
        //todo 因为当前只需要一个线程池，如果后续有多个线程池考虑由线程池名称来遍历获取
        log.info("线程池配置：{}", JSON.toJSONString(threadPoolProperty));
        ThreadPoolProperty.ThreadPoolDetail threadPoolDetail = threadPoolProperty.getThreadPoolDetailList().get(0);
        return new ThreadPoolExecutor(
                threadPoolDetail.getCorePoolSize(),
                threadPoolDetail.getMaxPoolSize(),
                threadPoolDetail.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(threadPoolDetail.getQueueSize()),
                threadFactoryBuilder.build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
