package supermarket.manage.system.service.finance.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
import supermarket.manage.system.service.finance.FinanceService;

/**
* @author ASUS
* @description 针对表【finance】的数据库操作Service实现
* @createDate 2024-05-07 00:19:04
*/
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance>
    implements FinanceService {

}




