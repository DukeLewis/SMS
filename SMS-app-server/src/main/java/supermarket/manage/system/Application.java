package supermarket.manage.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/7
 * @Copyright： https://github.com/DukeLewis
 */
@SpringBootApplication
@MapperScan("supermarket.manage.system.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
