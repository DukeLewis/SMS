package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 员工信息DTO
 * @author：dukelewis
 * @date: 2024/4/8
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "员工信息DTO")
public class EmployeeInfoDTO implements Serializable {


    /**
     * 员工唯一标识
     */
    @ApiModelProperty(value = "员工唯一标识",position = 1)
    @NotNull
    private Integer eid;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名")
    @NotBlank
    private String ename;

    /**
     * 员工性别
     */
    @ApiModelProperty(value = "员工性别")
    @NotBlank
    private String esex;

    /**
     * 岗位
     */
    @ApiModelProperty(value = "岗位")
    @NotBlank
    private String position;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    @NotBlank
    private String ephone;

    /**
     * 逻辑删除字段
     */
    @ApiModelProperty(value = "逻辑删除字段")
    @NotNull
    private Integer isDeleted;
}
