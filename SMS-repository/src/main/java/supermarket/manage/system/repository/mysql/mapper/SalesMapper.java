package supermarket.manage.system.repository.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import supermarket.manage.system.model.domain.Sales;
import supermarket.manage.system.model.dto.SalesInfoDTO;

import java.util.List;

/**
* @author ASUS
* @description 针对表【sales】的数据库操作Mapper
* @createDate 2024-05-07 00:19:13
* @Entity generator.domain.Sales
*/
public interface SalesMapper extends BaseMapper<Sales> {

    List<Sales> getByTime(String time);
    List<SalesInfoDTO> listall(Integer isdeleted);
    List<SalesInfoDTO>  listbygname(String gname);
    List<SalesInfoDTO> listbysaler(String saler);
}




