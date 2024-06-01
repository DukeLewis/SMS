package supermarket.manage.system.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName goods
 */
@TableName(value ="goods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    /**
     * 商品唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer gId;

    /**
     * 商品名称
     */
    private String gName;

    /**
     * 进货价格
     */
    private String purchasePrice;

    /**
     * 当前商品库存
     */
    private Integer inventory;

    /**
     * 库存阈值
     */
    private Integer inventoryThreshold;

    /**
     * 销售价格
     */
    private String sellPrice;

    /**
     * 商品品牌
     */
    private String gBrand;

    /**
     * 商品类别
     */
    private String gCategory;

    /**
     * 商品型号
     */
    private String gType;

    /**
     * 商品规格
     */
    private String gSpecs;

    /**
     * 商品产地
     */
    private String gOrigin;

    /**
     * 能供应该商品的供应商id列表
     */
    private String supplierIdList;

    /**
     * 能提供该商品的供应商对应的价格列表
     */
    private String supplierPriceList;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除字段
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
