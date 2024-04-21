package supermarket.manage.system.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @description: 进货单信息DTO
 * @author：dukelewis
 * @date: 2024/4/21
 * @Copyright： https://github.com/DukeLewis
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("进货单信息DTO")
public class RestockInfoDTO {

    /**
     * 进货单唯一标识
     */
    @ApiModelProperty(value = "进货单唯一标识")
    @NotNull
    private Integer rId;

    /**
     * 商品id列表
     */
    @ApiModelProperty(value = "商品id列表")
    @NotNull
    private List<String> productIdList;

    /**
     * 对应商品列表中的每个商品数量
     */
    @ApiModelProperty(value = "对应商品列表中的每个商品数量")
    @NotNull
    private List<String> productNumberList;

    /**
     * 对应商品列表中的每个商品是由哪个供应商进行供货
     */
    @ApiModelProperty(value = "对应商品列表中的每个商品是由哪个供应商进行供货")
    @NotNull
    private List<String> supplierList;

    /**
     * 商品进价列表，对应上面的商品列表中的每个商品中的进价
     */
    @ApiModelProperty(value = "商品进价列表，对应上面的商品列表中的每个商品中的进价")
    @NotNull
    private List<String> productPricelist;

    /**
     * 到货时间
     */
    @ApiModelProperty(value = "到货时间")
    @NotNull
    private Date arriveTime;

    /**
     * 进货单状态位，0-已下单未到货，1-已到货，2-待定，3-延期
     */
    @ApiModelProperty(value = "进货单状态位，0-已下单未到货，1-已到货，2-待定，3-延期")
    @NotNull
    private Integer status;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")
    @NotNull
    private Integer isDeleted;

}
