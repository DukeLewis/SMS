package supermarket.manage.system.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/15
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("库存信息DTO")
public class InventoryInfoDTO {
    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    @NotNull
    private Integer id;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @NotNull
    private Integer gid;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @NotNull
    private String gname;

    /**
     * 商品类别
     */
    @ApiModelProperty(value = "商品类别")
    @NotBlank
    private String gcategory;

    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    @NotNull
    @Min(value = 0, message = "入库数量不能小于0")
    private Integer inboundNum;

    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间")
    @NotNull
    private Date inboundTime;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商")
    @NotNull
    private String supplier;

    /**
     * 出库数量
     */
    @ApiModelProperty(value = "出库数量")
    @NotNull
    @Min(value = 0, message = "出库数量不能小于0")
    private Integer outboundNum;

    /**
     * 出库时间
     */
    @ApiModelProperty(value = "出库时间")
    @NotNull
    private Date outboundTime;

    /**
     * 出库用途
     */
    @ApiModelProperty(value = "出库用途")
    @NotBlank
    private String purpose;
}
