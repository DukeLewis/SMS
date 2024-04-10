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
 * @TableName goods
 */
@TableName(value ="goods")
@Data
@Builder
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
     * 能供应该商品的供应商列表
     */
    private String supplierList;

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
