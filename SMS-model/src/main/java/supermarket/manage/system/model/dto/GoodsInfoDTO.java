package supermarket.manage.system.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 商品信息DTO
 * @author：dukelewis
 * @date: 2024/4/10
 * @Copyright： https://github.com/DukeLewis
 */
@ApiModel(value = "商品信息DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfoDTO implements Serializable {
    /**
     * 商品唯一标识
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "商品唯一标识")
    @NotNull
    private Integer gid;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @NotBlank
    private String gname;

    /**
     * 进货价格
     */
    @ApiModelProperty(value = "进货价格")
    @NotBlank
    private String purchasePrice;

    /**
     * 当前商品库存
     */
    @ApiModelProperty(value = "当前商品库存")
    @NotNull
    private Integer inventory;

    /**
     *
     */
    @ApiModelProperty(value = "库存阈值")
    @NotNull
    private Integer inventoryThreshold;

    /**
     * 销售价格
     */
    @ApiModelProperty(value = "销售价格")
    @NotBlank
    private String sellPrice;

    /**
     * 商品品牌
     */
    @ApiModelProperty(value = "商品品牌")
    @NotBlank
    private String gbrand;

    /**
     * 商品类别
     */
    @ApiModelProperty(value = "商品类别")
    @NotBlank
    private String gcategory;

    /**
     * 商品型号
     */
    @ApiModelProperty(value = "商品型号")
    @NotBlank
    private String gtype;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    @NotBlank
    private String gspecs;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    @NotBlank
    private String gorigin;

    /**
     * 能供应该商品的供应商列表
     */
    @ApiModelProperty(value = "能供应该商品的供应商列表")
    @NotBlank
    private String supplierList;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")
    @NotNull
    private Integer isDeleted;
}
