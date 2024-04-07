package supermarket.manage.system.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.User;

import java.util.Map;

/**
 * 关于登录注册的接口
 */
public interface IAuthService extends IService<User> {
    Map<String,String> authorize(String username, String password,String code);

    boolean register(String username, String password);
}
