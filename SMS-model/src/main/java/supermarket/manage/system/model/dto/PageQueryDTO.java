package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 分页查询DTO
 * @author：dukelewis
 * @date: 2024/4/21
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页查询DTO")
public class PageQueryDTO implements Serializable {

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
     * 查询条件
     */
    @ApiModelProperty(value = "查询条件")
    private String keyword;
}
