package supermarket.manage.system.model.dto;



import com.baomidou.mybatisplus.annotation.FieldStrategy;
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

/**
 * @description: 财务记录DTO
 * @author：dukelewis
 * @date: 2024/5/6
 */
@ApiModel(value = "财务记录DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FinanceInfoDTO implements Serializable {

    /**
     * 财务记录唯一标识
     */
    @ApiModelProperty(value = "财务记录唯一标识")
    @NotNull
    private Integer fid;

    /**
     * 记录日期
     */
    @ApiModelProperty(value = "记录日期")
    @NotNull
    private Date recordTime;

    /**
     * 收入
     */
    @ApiModelProperty(value = "收入")
    @NotBlank
    private String revenue;

    /**
     * 采购成本
     */
    @ApiModelProperty(value = "采购成本")
    @NotBlank
    private String costs;

    /**
     * 水费
     */
    @ApiModelProperty(value = "水费")
    @NotBlank
    private String waterCost;

    /**
     * 电费
     */
    @ApiModelProperty(value = "电费")
    @NotBlank
    private String eleCost;

    /**
     * 支出
     */
    @ApiModelProperty(value = "收入")
    @NotBlank
    private String spend;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @NotNull
    private Date updateTime;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @NotNull
    private Date createTime;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")
    @NotNull
    private Integer isDeleted;





}
