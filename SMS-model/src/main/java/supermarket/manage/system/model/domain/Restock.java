package supermarket.manage.system.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @TableName restock
 */
@TableName(value ="restock")
@Data
@Builder
public class Restock implements Serializable {
    /**
     * 进货单唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer rId;

    /**
     * 商品id列表
     */
    private String productIdList;

    /**
     * 商品数量列表
     */
    private Integer productNumberList;

    /**
     * 对应商品列表中的每个商品是由哪个供应商进行供货，多个用逗号隔开
     */
    private String supplierList;

    /**
     * 商品进价列表，对应上面的商品列表中的每个商品中的进价，多个用逗号隔开
     */
    private String productPricelist;

    /**
     * 到货时间
     */
    private Date arriveTime;

    /**
     * 进货单状态位，0-已下单未到货，1-已到货，2-待定，3-延期
     */
    private Integer status;

    /**
     * 创建时间，同时也是下单时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除字段
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
