package supermarket.manage.system.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName finance
 */
@TableName(value ="finance")
@Data
public class Finance implements Serializable {
    /**
     * 财务记录唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer fId;

    /**
     * 记录日期
     */
    private Date recordTime;

    /**
     * 收入
     */
    private Double revenue;

    /**
     * 采购成本
     */
    private Double costs;

    /**
     * 水费
     */
    private Double waterCost;

    /**
     * 电费
     */
    private Double eleCost;

    /**
     * 支出
     */
    private Double spend;

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
