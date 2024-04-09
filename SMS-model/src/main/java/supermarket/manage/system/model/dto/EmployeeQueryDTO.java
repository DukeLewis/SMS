package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.*;

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
public class EmployeeQueryDTO {

    /**
     * 查询条件
     */
    @ApiParam(value = "查询条件",required = true)
    private Map<String,String> map;
}
