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
@ApiModel(value = "供应商信息DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInfoDTO implements Serializable {

    /**
     * 供应商唯一标识
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "供应商唯一标识")
    @NotNull
    private Integer sid;

    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    @NotBlank
    private String sname;

    /**
     * 负责人姓名
     */
    @ApiModelProperty(value = "负责人姓名")
    @NotBlank
    private String sprincipal;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @NotNull
    private String sphone;

    /**
     * 供应商地址
     */
    @ApiModelProperty(value = "供应商地址")
    @NotNull
    private String saddress;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")

    private Date updatetime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")

    private Date createTime;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")
    @NotNull
    private Integer isDeleted;


}
