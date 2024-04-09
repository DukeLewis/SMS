package supermarket.manage.system.service.auth.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.AuthStatus;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.common.util.JwtUtils;
import supermarket.manage.system.model.domain.User;
import supermarket.manage.system.model.entity.AuthUserEntity;
import supermarket.manage.system.repository.mysql.mapper.UserMapper;
import supermarket.manage.system.service.auth.IAuthService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class AuthService extends ServiceImpl<UserMapper, User> implements IAuthService {


    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder bcryptPasswordEncoder;


    @Override
    public Map<String,String> authorize(String username, String password){
        log.info("开始验证----");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_LOGIN));
        }
        //获取验证成功的用户，包含账号以及id
        AuthUserEntity user = (AuthUserEntity) authenticate.getPrincipal();
        username = user.getUsername();
        log.info("开始封装返回的jwt");
        //开始封装返回的token
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        chaim.put("id", user.getId());
        Map<String, String> map = new HashMap<>();
        String jwtToken = JwtUtils.encode(username, 30 * 60 * 1000, chaim);
        map.put("msg", AuthStatus.SUCESS.getMsg());
        map.put("token", jwtToken);
        return map;
    }

    @Override
    public boolean register(String username, String password) {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_USER_PWD_NULL));
        }
        if(this.getOne(new QueryWrapper<User>().eq(Constant.USERNAME, username))!=null){
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_USER_EXISTS));
        }
        return this.save(User.builder()
                .uName(username)
                //用户权限默认为0
                .uPermission(0)
                .password(bcryptPasswordEncoder.encode(password))
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(0).build());
    }
}
