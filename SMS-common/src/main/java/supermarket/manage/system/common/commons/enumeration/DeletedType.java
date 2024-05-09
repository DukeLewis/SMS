package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@AllArgsConstructor
public enum DeletedType {
    DELETED(1,"已删除"),
    UN_DELETED(0,"未删除");

    /**
     *  状态码
     */
    private final Integer code;

    /**
     * 描述信息
     */
    final String info;
}
