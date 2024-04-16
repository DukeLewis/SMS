package supermarket.manage.system.autoconfig.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import supermarket.manage.system.autoconfig.property.ThreadPoolProperty;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@EnableConfigurationProperties(ThreadPoolProperty.class)
public class ThreadPoolConfig {

}
