package supermarket.manage.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class AuthVO implements Serializable {

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息",required = true)
    private String msg;

    /**
     * token
     */
    @ApiModelProperty(value = "登录成功返回token",required = false)
    private String token;

    public AuthVO(String msg) {
        this.msg = msg;
    }
}
