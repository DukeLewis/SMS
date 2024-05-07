package supermarket.manage.system.service.sales.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.model.domain.Sales;
import supermarket.manage.system.repository.mysql.mapper.SalesMapper;
import supermarket.manage.system.service.sales.SalesService;

/**
* @author ASUS
* @description 针对表【sales】的数据库操作Service实现
* @createDate 2024-05-07 00:19:13
*/
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales>
    implements SalesService {

}




