package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.*;

/**
 * @description: 登录注册DTO
 * @author：dukelewis
 * @date: 2024/4/9
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户登录注册DTO")
public class AuthDTO {

    /**
     * 用户名
     */
    @ApiParam(name = "用户名",required = true)
    private String username;

    /**
     * 密码
     */
    @ApiParam(name = "密码", required = true)
    private String password;
}
