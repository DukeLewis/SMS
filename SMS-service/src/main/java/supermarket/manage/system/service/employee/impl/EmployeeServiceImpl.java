package supermarket.manage.system.service.employee.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.model.domain.Employee;
import supermarket.manage.system.repository.mysql.mapper.EmployeeMapper;
import supermarket.manage.system.service.employee.IEmployeeService;

import javax.annotation.Resource;

/**
* @author ASUS
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2024-04-08 22:45:51
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;



}




