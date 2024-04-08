package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 登录状态枚举
 * @author：dukelewis
 * @date: 2024/4/8
 * @Copyright： https://github.com/DukeLewis
 */
@AllArgsConstructor
@Getter
public enum AuthStatus {

    SUCESS("登录成功"),
    FAIL("登录失败"),
    DUPLICATE("用户名已存在"),
    INCORRECT("用户名或密码错误"),
    BLANK("用户名或密码不能为空");

    private final String msg;
}
