package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 模块类型枚举，如财务模块，用户模块
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
@AllArgsConstructor
@Getter
public enum ModuleType {
    FINANCE("finance"),
    GOODS("goods"),
    INVENTORY("inventory"),
    EMPLOYEE("employee"),
    SUPPLIER("supplier"),
    RESTOCK("restock"),
    SALES("sales"),
    AUTH("auth");

    final String info;

    public boolean equal(ModuleType moduleType) {
        return this.info.equals(moduleType.info);
    }
}
