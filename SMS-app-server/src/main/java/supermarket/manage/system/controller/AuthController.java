package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/login")
    @ApiOperation("用户登录")
    public String login(@NotNull @RequestBody Map<String,String> map){
        return "login";
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public String register(@NotNull @RequestBody Map<String,String> map){
        return "register";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
