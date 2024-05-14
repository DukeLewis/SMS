package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 操作数据的类型枚举，如：增删改查
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@AllArgsConstructor
@Getter
public enum InfoType {
    QUERY("查询"),
    ADD("添加"),
    DEL("删除"),
    MODIFY("修改"),
    ;
    private final String info;
}
