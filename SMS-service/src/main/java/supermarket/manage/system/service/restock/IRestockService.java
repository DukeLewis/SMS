package supermarket.manage.system.service.restock;


import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.Restock;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.RestockInfoDTO;
import supermarket.manage.system.model.vo.PageResult;

/**
* @author ASUS
* @description 针对表【restock】的数据库操作Service
* @createDate 2024-04-21 14:58:04
*/
public interface IRestockService extends IService<Restock> {

    /**
     * 添加进货单
     * @param restockInfoDTO
     * @return
     */
    boolean addRestock(RestockInfoDTO restockInfoDTO);

    /**
     * 修改进货单
     * @param restockInfoDTO
     * @return
     */
    boolean updateRestock(RestockInfoDTO restockInfoDTO);

    /**
     * 查询全部进货单
     * @param pageQueryDTO
     * @return
     */
    PageResult queryRestockALL(PageQueryDTO pageQueryDTO);

    /**
     * 查询进货单
     * @param pageQueryDTO
     * @return
     */
    PageResult queryRestock(PageQueryDTO pageQueryDTO);
}
