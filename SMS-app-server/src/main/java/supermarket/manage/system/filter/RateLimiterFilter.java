package supermarket.manage.system.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @description: 限流过滤器
 * @author：dukelewis
 * @date: 2024/4/18
 * @Copyright： https://github.com/DukeLewis
 */
@Deprecated
@WebFilter(filterName = "RateLimiterFilter", urlPatterns = "/*")
@Order(1)
public class RateLimiterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
