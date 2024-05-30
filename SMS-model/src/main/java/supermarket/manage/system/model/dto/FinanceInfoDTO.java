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

    private Date recordTime;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型，1为支出2为收入")

    private Integer ftype;

    /**
     * 数额
     */
    @ApiModelProperty(value = "数额")

    private Double amount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")

    private String remark;



    /**
     * 时间类型(年，月，日）
     */
    @ApiModelProperty(value = "时间类型(年，月，日）",allowableValues = "day,month,year")

    private String timeType;

    @ApiModelProperty(value = "哪年，哪月，哪天")
    private String time;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")

    private Date updateTime;


    @ApiModelProperty(value = "销售唯一标识")

    private Integer sid;

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
