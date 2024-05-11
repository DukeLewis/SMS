package supermarket.manage.system.model.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "销售信息DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesInfoDTO implements Serializable {
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
     * 商品单价
     */
    @ApiModelProperty(value = "商品单价")
    @NotBlank
    private Double gPrice;

    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    @NotNull
    private Integer saleNum;

    /**
     * 销售时间
     */
    @ApiModelProperty(value = "销售时间")
    @NotNull
    private Date saleTime;

    /**
     * 销售员
     */
    @ApiModelProperty(value = "销售员")
    @NotBlank
    private String saler;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @NotBlank
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @NotBlank
    private Date createTime;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")
    @NotNull
    private Integer isDeleted;


}
