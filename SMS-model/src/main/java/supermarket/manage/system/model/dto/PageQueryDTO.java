package supermarket.manage.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
     * 查询条件（分类，名称，销售员，id，日期）
     * id：如果对应的是商品查询，那么就是商品id，如果对应的是进货单查询，那么就是进货单id
     * 分类：如果对应的是商品查询，那么就是商品分类，如果对应的是支出查询，那么就是支出类型
     * 名称：如果对应的是商品查询，那么就是商品名称，如果对应的是员工查询，那么就是员工名称
     * 销售员：用于财务查询，根据销售员信息进行查询
     */
    @ApiModelProperty(value = "查询条件（全部，分类，名称，销售员，id，日期）," +
            "id：如果对应的是商品查询，那么就是商品id，如果对应的是进货单查询，那么就是进货单id." +
            "分类：如果对应的是商品查询，那么就是商品分类，如果对应的是支出查询，那么就是支出类型." +
            "名称：如果对应的是商品查询，那么就是商品名称，如果对应的是员工查询，那么就是员工名称." +
            "销售员：用于财务查询，根据销售员信息进行查询"
            ,allowableValues = "category,name,salesman,id,time")
    private String keywordType;

    /**
     * 查询关键字的值
     */
    @ApiModelProperty(value = "查询关键字的值")
    private String keyword;
}
