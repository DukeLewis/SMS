package supermarket.manage.system.service.auth;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.util.JwtUtils;
import supermarket.manage.system.model.entity.SimpleUserEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token检查过滤器
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("进入过滤器");
        //清除上下文（清除认证信息）
        SecurityContextHolder.clearContext();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        logger.info("验证jwt");
        Claims claims = null;
        try {
            if (!StringUtils.hasText(token)) {
                filterChain.doFilter(request, response);
                log.info("token为空");
                return;
            }
            claims = JwtUtils.getClaims(token);
        } catch (ExpiredJwtException e) {
            log.debug("解析JWT失败，JWT过期:{},{}", e.getClass().getName(), e.getMessage());
            String errorMessage = "登录信息已过期，请重新登录";
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_EXPIRED);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (SignatureException e) {
            log.debug("解析JWT失败，签名错误:{},{}", e.getClass().getName(), e.getMessage());
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_INVALID);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (MalformedJwtException e) {
            log.debug("解析JWT失败，JWT数据有误:{},{}", e.getClass().getName(), e.getMessage());
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_INVALID);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (Throwable e) {
            log.debug("解析JWT失败，错误详情:{},{}", e.getClass().getName(), e.getMessage());
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_INVALID);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        }

        long id = Long.parseLong(String.valueOf(claims.get("id")));

        SimpleUserEntity simpleUserEntity = new SimpleUserEntity(id, (String) claims.get("username"));

        //todo 后续如果需要鉴权可以传入权限列表
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(simpleUserEntity, null, null);
        //放置上下文
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
