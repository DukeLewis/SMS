package supermarket.manage.system.repository.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import supermarket.manage.system.model.domain.Inventory;

/**
* @author ASUS
* @description 针对表【inventory】的数据库操作Mapper
* @createDate 2024-04-15 19:52:14
* @Entity generator.domain.Inventory
*/
public interface InventoryMapper extends BaseMapper<Inventory> {
    Inventory selectByGID(Integer gid);

}




