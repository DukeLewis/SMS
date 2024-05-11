package supermarket.manage.system.service.inventory;


import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.Inventory;
import supermarket.manage.system.model.dto.InventoryInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;

/**
* @author ASUS
* @description 针对表【inventory】的数据库操作Service
* @createDate 2024-04-15 19:52:14
*/
public interface IInventoryService extends IService<Inventory> {

    /**
     * 添加库存信息
     * @param inventoryInfoDTO
     * @return
     */
    boolean addInventory(InventoryInfoDTO inventoryInfoDTO);

    /**
     * 删除库存信息
     * @param id
     * @return
     */
    boolean delInventory(Integer id);


    /**
     * 查询某个商品库存信息
     * @param pageQueryDTO
     * @return
     */
    PageResult queryInventory(PageQueryDTO pageQueryDTO);


    /**
     * 查询所有商品库存信息
     * @return
     */
    PageResult queryInventoryAll(PageQueryDTO pageQueryDTO);
}
