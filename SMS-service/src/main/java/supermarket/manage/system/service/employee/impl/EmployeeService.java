package supermarket.manage.system.service.employee.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.model.domain.Employee;
import supermarket.manage.system.model.dto.EmployeeInfoDTO;
import supermarket.manage.system.model.dto.EmployeeQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.EmployeeMapper;
import supermarket.manage.system.service.employee.IEmployeeService;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author ASUS
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2024-04-08 22:45:51
*/
@Service
public class EmployeeService extends ServiceImpl<EmployeeMapper, Employee>
    implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public boolean informationEntry(EmployeeInfoDTO employeeInfoDTO) {
        return save(Employee.builder().eName(employeeInfoDTO.getEname())
                .eSex(employeeInfoDTO.getEsex())
                .position(employeeInfoDTO.getPosition())
                .ePhone(employeeInfoDTO.getEphone())
                .updateTime(new Date())
                .createTime(new Date())
                .isDeleted(0).build());
    }

    @Override
    public boolean informationModification(EmployeeInfoDTO employeeInfoDTO) {
        return updateById(Employee.builder()
                .eId(employeeInfoDTO.getEid())
                .eName(employeeInfoDTO.getEname())
                .eSex(employeeInfoDTO.getEsex())
                .position(employeeInfoDTO.getPosition())
                .ePhone(employeeInfoDTO.getEphone())
                .updateTime(new Date())
                .isDeleted(employeeInfoDTO.getIsDeleted()).build());
    }

    @Override
    public PageResult informationQuery(EmployeeQueryDTO employeeQueryDTO) {
        Integer pag = employeeQueryDTO.getPage();
        Integer pagesize = employeeQueryDTO.getPagesize();

        Page<Employee> page = employeeMapper.selectPage(
                new Page<Employee>(pag,pagesize),
                new QueryWrapper<Employee>().eq(Constant.EMPLOYEE_NAME, employeeQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED,1)
        );
        return new PageResult(
                pag,pagesize,page.getTotal(),page.getRecords()
        );
    }
}




