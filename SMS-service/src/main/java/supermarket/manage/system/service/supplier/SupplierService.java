package supermarket.manage.system.service.supplier;

import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.Supplier;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SupplierInfoDTO;
import supermarket.manage.system.model.dto.SupplierPageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;

public interface SupplierService extends IService<Supplier> {

    /**
     * 供应商信息录入
     * @param supplierInfoDTO
     * @return
     */
    boolean informationEntry(SupplierInfoDTO supplierInfoDTO);

    /**
     * 供应商信息修改
     * @param supplierInfoDTO
     * @return
     */
    boolean informationModification(SupplierInfoDTO supplierInfoDTO);


    /**
     * 供应商信息删除
     * @param supplierInfoDTO
     * @return
     */
    boolean informationDeletion(SupplierInfoDTO supplierInfoDTO);

    /**
     *所有供应商信息查询,返回信息做分页处理
     * @param pageQueryDTO
     * @return
     */
    PageResult informationQueryALL(PageQueryDTO pageQueryDTO);

    /**
     * 供应商信息查询,返回信息做分页处理
     * @param pageQueryDTO
     * @return
     */
    PageResult informationQuery(PageQueryDTO pageQueryDTO);


    /**
     * 一个商品对应供应商信息查询
     * @param supplierPageQueryDTO
     * @return
     */
    PageResult querySupplier(SupplierPageQueryDTO supplierPageQueryDTO);

}
