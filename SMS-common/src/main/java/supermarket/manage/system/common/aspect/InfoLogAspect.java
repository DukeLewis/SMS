package supermarket.manage.system.common.aspect;

import cn.hutool.core.util.StrUtil;
import com.sun.xml.internal.ws.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import supermarket.manage.system.common.annotation.InfoLog;
import supermarket.manage.system.common.util.StringUtil;

import java.lang.reflect.Method;

/**
 * @description: 日志切面
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@Aspect
@Component
@Slf4j
public class InfoLogAspect {

    @Around("@annotation(infoLog)")
    public Object log(ProceedingJoinPoint pjq, InfoLog infoLog) {
        Object reponse = null;
        try {
            //目标方法执行
            reponse = pjq.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        if (StrUtil.isNotEmpty(infoLog.infoItemIdExpression())) {
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(infoLog.infoItemIdExpression());
            StandardEvaluationContext context = new StandardEvaluationContext();

            //获取方法参数值
            Object[] args = pjq.getArgs();

            //获取方法的所有的参数名称
            Method method = ((MethodSignature) pjq.getSignature()).getMethod();
            String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);

            //参数绑定到context
            if (parameterNames != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
            }

            //如果方法有返回值，那么可以从方法返回值中获取spel表达式所需要的值,将返回值名称转换成其所属类名的小写开头形式
            if (reponse != null) {
                context.setVariable(
                        StrUtil.lowerFirst(reponse.getClass().getSimpleName()),
                        reponse
                );
            }

            //解析表达式获取结果
            String itemId = String.valueOf(expression.getValue(context));

            //输出日志
            log.info("infoType=" + infoLog.infoType() + ",infoItemId=" + itemId + ",infoItem=" + infoLog.item());

        }
        return reponse;
    }

}
