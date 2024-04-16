package supermarket.manage.system.model.entity;

import io.swagger.models.auth.In;
import lombok.*;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryEventEntity {

    /**
     * 商品id
     */
    private Integer gid;

    /**
     * 商品名称
     */
    private String gname;

    /**
     * 类型（入库，出库）
     */
    private String type;

    /**
     * 数量
     */
    private Integer number;

    @Getter
    @AllArgsConstructor
    public enum Type {
        IN("入库"),
        OUT("出库");
        private String type;
    }
}
