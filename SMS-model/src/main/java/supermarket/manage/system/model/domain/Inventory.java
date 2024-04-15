package supermarket.manage.system.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @TableName inventory
 */
@TableName(value ="inventory")
@Data
@Builder
public class Inventory implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer gId;

    /**
     * 商品名称
     */
    private String gName;

    /**
     * 商品类别
     */
    private String gCategory;

    /**
     * 入库数量
     */
    private Integer inboundNum;

    /**
     * 入库时间
     */
    private Date inboundTime;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 出库数量
     */
    private Integer outboundNum;

    /**
     * 出库时间
     */
    private Date outboundTime;

    /**
     * 出库用途
     */
    private String purpose;

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
