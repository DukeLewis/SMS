package supermarket.manage.system.model.entity;

import lombok.*;

import java.util.Date;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/21
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestockWarnEntity {

    /**
     * 进货单id
     */
    private Integer rId;

    /**
     * 到货时间
     */
    private Date arriveTime;
}
