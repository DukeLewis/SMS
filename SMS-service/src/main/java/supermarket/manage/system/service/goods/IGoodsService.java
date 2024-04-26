package supermarket.manage.system.service.goods;


import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;

/**
* @author ASUS
* @description 针对表【goods】的数据库操作Service
* @createDate 2024-04-10 23:24:59
*/
public interface IGoodsService extends IService<Goods> {


    /**
     * 商品信息录入
     * @param employeeInfoDTO
     * @return
     */
    boolean informationEntry(GoodsInfoDTO employeeInfoDTO);


    /**
     * 商品信息修改
     * @param employeeInfoDTO
     * @return
     */
    boolean informationModification(GoodsInfoDTO employeeInfoDTO);

    /**
     * 商品信息查询,返回信息做分页处理
     * @param pageQueryDTO
     * @return
     */
    PageResult informationQuery(PageQueryDTO pageQueryDTO);

}
