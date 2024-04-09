package supermarket.manage.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/9
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户登录注册返回对象")
public class AuthVO {

    /**
     * 提示信息
     */
    @ApiParam(name = "提示信息",required = true)
    private String msg;

    /**
     * token
     */
    @ApiParam(name = "登录成功返回token",required = false)
    private String token;

    public AuthVO(String msg) {
        this.msg = msg;
    }
}
