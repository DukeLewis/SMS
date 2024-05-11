package supermarket.manage.system.model.entity;

import lombok.*;

/**
 * @description: 查询一个商品对应的供应商列表所需要的entity
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuerySupplierListOfGoodsEntity {

    /**
     * 供应商id
     */
    private Integer sId;

    /**
     * 供应商名称
     */
    private String sName;

    /**
     * 负责人姓名
     */
    private String sPrincipal;

    /**
     * 联系电话
     */
    private String sPhone;

    /**
     * 供应商地址
     */
    private String sAddress;


}
