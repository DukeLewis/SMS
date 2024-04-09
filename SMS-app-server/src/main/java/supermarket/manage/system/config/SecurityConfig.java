package supermarket.manage.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import supermarket.manage.system.service.auth.JwtAuthenticationTokenFilter;

import javax.annotation.Resource;
import java.util.Collections;

@Configuration
/**
 * @EnableWebSecurity是开启SpringSecurity的默认行为
 */
@EnableWebSecurity
//是否开启对于方法的权限认证
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

//    @Value("${app.config.api-version}")
//    private String apiVersion;


//
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(AuthenticationManager authenticationManager)   {
//        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }

    /**
     * 密码明文加密方式配置
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 开启跨域以便前端调用接口
                .cors().and()
                .authorizeRequests()
                // 指定某些接口不需要通过验证即可访问。登录接口肯定是不需要认证的
                .antMatchers("/doc.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/favicon.ico",
                        "/swagger-resources",
                        "/v2/api-docs",
                        "/user/login",
                        "/user/hello",
                        "/user/register",
                        "/employee/enter"
                ).permitAll()
                // 静态资源，可匿名访问
//                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**","/doc.html").permitAll()
                // 这里意思是其它所有接口需要鉴权认证才能访问
                .anyRequest().authenticated()
                .and()
                // 基于 token，不需要 session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // cors security 解决方案
                .cors().configurationSource(corsConfigurationSource())
                .and()
//                 添加处理JWT过滤器，必须执行在处理用户名、密码的过滤器(Security内置)之前 - token验证成功了无需登录
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }




    /**
     * 配置跨源访问(CORS)
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
