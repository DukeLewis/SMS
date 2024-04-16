package supermarket.manage.system.common.annotation;

import supermarket.manage.system.common.commons.enumeration.InfoType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 日志注解
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoLog {

    /**
     * 业务类型，增删改查
     * @return
     */
    public InfoType infoType();

    /**
     * 业务对象名称，如：订单，库存
     * @return
     */
    public String item();

    /**
     * 获取业务对象的spel 表达式，描述如何获取标识id
     * @return
     */
    public String infoItemIdExpression();
}
