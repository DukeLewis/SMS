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
 * @TableName supplier
 */
@TableName(value ="supplier")
@Data
public class Supplier implements Serializable {
    /**
     * 供应商唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer sId;

    /**
     * 供应商名称
     */
    private String sName;

    /**
     * 负责人姓名
     */
    private String sPrincipal;

    /**
     * 联系电话
     */
    private String sPhone;

    /**
     * 供应商地址
     */
    private String sAddress;

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
