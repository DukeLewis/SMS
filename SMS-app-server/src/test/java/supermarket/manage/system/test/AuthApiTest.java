package supermarket.manage.system.test;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import supermarket.manage.system.service.auth.IAuthService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 登录注册服务模块测试类
 * @author：dukelewis
 * @date: 2024/4/8
 * @Copyright： https://github.com/DukeLewis
 */
@SpringBootTest
@Slf4j
public class AuthApiTest {


    @Resource
    private IAuthService authService;


    /**
     * 测试注册服务
     */
    @Test
    public void testReg() {
        authService.register("admin","123456");
    }


    /**
     * 测试登录
     */
    @Test
    public void testLogin() {
        Map<String, String> map = authService.authorize("admin", "123456");
        log.info("登录结果：{}", JSON.toJSONString(map));
    }

}
