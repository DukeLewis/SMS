package supermarket.manage.system.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName sales
 */
@TableName(value ="sales")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sales implements Serializable {
    /**
     * 销售记录唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer sId;

    /**
     * 商品id
     */
    private Integer gId;



    /**
     * 销售数量
     */
    private Integer saleNum;

    /**
     * 销售时间
     */
    private Date saleTime;

    /**
     * 销售员
     */
    private String saler;



    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除字段
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
