package supermarket.manage.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/15
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryWarnEntity {

    /**
     * 商品唯一标识
     */
    private Integer gId;

    /**
     * 商品名称
     */
    private String gName;

    /**
     * 当前商品库存
     */
    private Integer inventory;

    /**
     * 库存阈值
     */
    private Integer inventoryThreshold;

}
