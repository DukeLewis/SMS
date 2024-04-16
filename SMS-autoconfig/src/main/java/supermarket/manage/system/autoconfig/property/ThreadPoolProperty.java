package supermarket.manage.system.autoconfig.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@ConfigurationProperties(prefix = "thread.pool",ignoreInvalidFields = true)
public class ThreadPoolProperty {
    private Integer count;

    private List<ThreadPoolDetail> threadPoolDetailList;

    public static class ThreadPoolDetail{
        private String name;
        private Integer corePoolSize;
        private Integer maxPoolSize;
        private Long keepAliveTime;
        private Integer queueSize;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public Integer getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(Integer maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public Long getKeepAliveTime() {
            return keepAliveTime;
        }

        public void setKeepAliveTime(Long keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
        }

        public Integer getQueueSize() {
            return queueSize;
        }

        public void setQueueSize(Integer queueSize) {
            this.queueSize = queueSize;
        }
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ThreadPoolDetail> getThreadPoolDetailList() {
        return threadPoolDetailList;
    }

    public void setThreadPoolDetailList(List<ThreadPoolDetail> threadPoolDetailList) {
        this.threadPoolDetailList = threadPoolDetailList;
    }
}
