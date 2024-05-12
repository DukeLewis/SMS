package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@AllArgsConstructor
public enum FinanceType {
    DAY("day"),
    MONTH("month"),
    YEAR("year");

    private final String info;

    public boolean equal(FinanceType financeType) {
        return this.info.equals(financeType.getInfo());
    }

}
