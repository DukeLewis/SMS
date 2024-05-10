package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.AuthDTO;
import supermarket.manage.system.model.vo.AuthVO;
import supermarket.manage.system.service.auth.IAuthService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/7
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@Api(tags = "1.用户登录注册模块")
@RequestMapping("/user")
public class AuthController {

    @Resource
    private IAuthService authService;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public AppResult<AuthVO> login(@NotNull @RequestBody AuthDTO authDTO){
        Map<String, String> map = authService.authorize(authDTO.getUsername(), authDTO.getPassword());
        AuthVO authVO = new AuthVO(map.get("msg"), map.get("token"));
        return AppResult.success(authVO);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public AppResult<AuthVO> register(@NotNull @RequestBody AuthDTO authDTO){
        boolean flg = authService.register(authDTO.getUsername(), authDTO.getPassword());
        AuthVO authVO = new AuthVO(flg == true ? "注册成功" : "注册失败");
        return AppResult.success(authVO);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
