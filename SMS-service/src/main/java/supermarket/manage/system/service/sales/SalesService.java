package supermarket.manage.system.service.sales;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.domain.Sales;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SalesInfoDTO;
import supermarket.manage.system.model.vo.PageResult;

/**
* @author ASUS
* @description 针对表【sales】的数据库操作Service
* @createDate 2024-05-07 00:19:13
*/
public interface SalesService extends IService<Sales> {
    /**
     * 销售信息录入
     * @param sales
     * @return
     */
    public boolean informationEntry(Sales sales);
    /**
     * 销售信息修改
     * @param salesInfoDTO
     * @return
     */
    public boolean informationModification( SalesInfoDTO salesInfoDTO);
    /**
     * 销售信息删除
     * @param salesInfoDTO
     * @return
     */

    public boolean informationDeletion( SalesInfoDTO salesInfoDTO);

    /**
     * 所有销售信息查询
     * @param pageQueryDTO
     * @return
     */
    public PageResult informationQueryALL(@NotNull PageQueryDTO pageQueryDTO);
    /**
     * 根据指定条件查询供应商信息,返回信息做分页处理
     * @param pageQueryDTO
     * @return
     */
    public PageResult informationQuery(@NotNull PageQueryDTO pageQueryDTO);

}
