package supermarket.manage.system.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.*;

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
public class EmployeeInfoDTO {

    /**
     * 员工姓名
     */
    @ApiParam(name = "员工姓名",required = true)
    private String eName;

    /**
     * 员工性别
     */
    @ApiParam(name = "员工性别")
    private String eSex;

    /**
     * 岗位
     */
    @ApiParam(name = "岗位")
    private String position;

    /**
     * 联系方式
     */
    @ApiParam(name = "联系方式")
    private String ePhone;
}
