package supermarket.manage.system.repository.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.omg.PortableInterceptor.INACTIVE;
import supermarket.manage.system.model.domain.Finance;

import java.util.List;

/**
* @author ASUS
* @description 针对表【finance】的数据库操作Mapper
* @createDate 2024-05-07 00:19:04
* @Entity generator.domain.Finance
*/
public interface FinanceMapper extends BaseMapper<Finance> {
    Integer updateBysId(Integer sid);

     List<Finance> selectallbysid(Integer sid) ;


}




