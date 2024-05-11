package supermarket.manage.system.model.dto;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class AuthDTO implements Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password;
}
