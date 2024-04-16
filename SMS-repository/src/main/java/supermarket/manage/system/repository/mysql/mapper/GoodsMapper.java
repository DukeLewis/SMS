package supermarket.manage.system.repository.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.entity.InventoryWarnEntity;

import java.util.List;

/**
* @author ASUS
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2024-04-10 23:24:59
* @Entity generator.domain.Goods
*/
public interface GoodsMapper extends BaseMapper<Goods> {


    /**
     * 查找库存预警商品
     * @return
     */
    public List<InventoryWarnEntity> findInventoryWarn();

}




