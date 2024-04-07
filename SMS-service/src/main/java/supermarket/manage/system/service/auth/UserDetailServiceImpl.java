package supermarket.manage.system.service.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import supermarket.manage.system.model.domain.User;
import supermarket.manage.system.model.entity.AuthUserEntity;
import supermarket.manage.system.repository.mysql.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder bcryptPasswordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过用户名查询密码进行比对然后进行返回
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if(user==null){
            return null;
        }
        return new AuthUserEntity(username, user.getPassword(), Collections.EMPTY_LIST, user.getUId().longValue());
    }
}
