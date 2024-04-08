package supermarket.manage.system.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 *  用户校验实体类对象
 */
public class AuthUserEntity extends User {

    /** 用户id */
    private Long id;

    public AuthUserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}