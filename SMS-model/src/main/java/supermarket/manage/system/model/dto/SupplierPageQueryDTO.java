package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import supermarket.manage.system.common.commons.Constant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description: 供应商分页查询DTO
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("供应商分页查询DTO")
@Getter
@Setter
@ToString
public class SupplierPageQueryDTO extends PageQueryDTO{

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @NotNull
    @Min(value = 1,message = "商品id不能小于1")
    private Integer id;

    /**
     * 排序类型，只允许匹配asc或者desc
     */
    @ApiModelProperty(value = "排序类型",allowableValues = "asc,desc")
    @NotBlank
    @Pattern(regexp = "^(asc|desc)$",message = "排序类型只能是asc或desc")
    private String sortType;

    /**
     * 排序关键字
     */
    @ApiModelProperty(value = "排序关键字")
    @NotBlank
    private String sortKey;
}
