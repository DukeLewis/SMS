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
     * 销售唯一标识
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "销售唯一标识")
    @NotNull
    private Integer sid;

    /**
     * 商品id
     */
//    @ApiModelProperty(value = "商品id")
//    @NotBlank
//    private Integer gid;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")

    private String gname;

    /**
     * 商品单价
     */
    @ApiModelProperty(value = "商品单价,不能更改")

    private Double gPrice;

    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")

    private Integer saleNum;



    /**
     * 销售时间
     */
    @ApiModelProperty(value = "销售时间")

    private Date saleTime;

    /**
     * 销售员
     */
    @ApiModelProperty(value = "销售员")

    private String saler;

    /**
     * 销售金额
     */
//    @ApiModelProperty(value = "销售金额")
//    @NotBlank
//    private Double salemoney;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")

    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")

    private Date createTime;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")

    private Integer isDeleted;


}
