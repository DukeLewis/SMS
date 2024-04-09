package supermarket.manage.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @description: 分页VO
 * @author：dukelewis
 * @date: 2024/4/9
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页返回对象")
public class PageResult implements Serializable {

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long counts=0L;

    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小")
    private Integer pagesize;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Long pages=0L;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private Integer page;

    /**
     * 列表数据
     */
    @ApiModelProperty(value = "列表数据")
    private List<?> items= Collections.emptyList();

    public PageResult(Integer page,Integer pagesize,
                      Long counts,List list){
        this.page=page;
        this.counts=counts;
        this.items=list;
        this.pagesize=pagesize;
        this.pages=counts%pagesize==0?counts/pagesize:counts/pagesize+1;
    }
}
