package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/10
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("商品信息查询DTO")
public class GoodsQueryDTO {

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
     * 查询条件（商品名称）
     */
    @ApiModelProperty(value = "查询条件（商品名称）")
    private String keyword;

}
