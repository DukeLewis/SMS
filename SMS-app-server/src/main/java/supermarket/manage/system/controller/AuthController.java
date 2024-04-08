package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.service.auth.IAuthService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/7
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@Api(tags = "1.用户登录注册模块")
@RequestMapping("user")
public class AuthController {

    @Resource
    private IAuthService authService;


    @GetMapping("/login")
    @ApiOperation("用户登录")
    public ResponseEntity login(@NotNull @RequestBody Map<String,String> map){
        return ResponseEntity.ok(authService.authorize(map.get("username"),map.get("password")));
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseEntity register(@NotNull @RequestBody Map<String,String> map){
        return ResponseEntity.ok(authService.register(map.get("username"),map.get("password"))==true?"注册成功":"注册失败");
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
