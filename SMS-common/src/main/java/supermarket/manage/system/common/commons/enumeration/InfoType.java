package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
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
