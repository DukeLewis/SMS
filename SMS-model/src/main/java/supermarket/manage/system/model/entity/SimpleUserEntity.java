package supermarket.manage.system.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单用户信息实体
 * 主要用于通过token验证后，将当前用户的信息存入上下文
 * @author ASUS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleUserEntity {

    /** 用户id*/
    private Long id;

    /** 用户名*/
    private String username;

}
