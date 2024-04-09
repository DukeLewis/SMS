package supermarket.manage.system.service.auth;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import supermarket.manage.system.common.util.JwtUtils;
import supermarket.manage.system.model.entity.SimpleUserEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException {
        logger.info("进入过滤器");
        //清除上下文（清除认证信息）
        SecurityContextHolder.clearContext();
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        logger.info("验证jwt");
        Claims claims = null;
        try {
            if (StringUtils.isEmpty(token)) {
                filterChain.doFilter(request, response);
                log.info("token为空");
                return;
            }
            claims = JwtUtils.getClaims(token);
            long id = Long.parseLong(String.valueOf(claims.get("id")));

            SimpleUserEntity simpleUserEntity = new SimpleUserEntity(id, (String) claims.get("username"));

            //todo 后续如果需要鉴权可以传入权限列表
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(simpleUserEntity, null, null);
            //放置上下文
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.info("token验证失败");
            throw new RuntimeException(e);
        }
    }
}
