package supermarket.manage.system.common.commons.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 进货单的状态
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@AllArgsConstructor
public enum RestockStatus {
    UN_ARRIVED(0,"未到货"),
    ARRIVED(1,"已到货"),
    PENDING(2,"待定"),
    DELAY(3,"延期");

    /**
     * 进货单状态
     */
    private final Integer status;

    /**
     * 进货单状态信息
     */
    private final String info;
}
