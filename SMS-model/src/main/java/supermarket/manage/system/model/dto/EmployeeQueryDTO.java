package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: 员工信息查询DTO
 * @author：dukelewis
 * @date: 2024/4/8
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("员工信息查询DTO")
public class EmployeeQueryDTO implements Serializable {


    /**
     * 分页
     */
    @ApiModelProperty(value = "分页")
    private Integer page=1;

    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小")
    private Integer pagesize=10;



    /**
     * 查询条件（姓名）
     */
    @ApiModelProperty(value = "查询条件（姓名）")
    private String keyword;
}
