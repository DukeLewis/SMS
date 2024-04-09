package supermarket.manage.system.service.employee;


import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.Employee;
import supermarket.manage.system.model.dto.EmployeeInfoDTO;
import supermarket.manage.system.model.dto.EmployeeQueryDTO;
import supermarket.manage.system.model.vo.PageResult;

/**
* @author ASUS
* @description 针对表【employee】的数据库操作Service
* @createDate 2024-04-08 22:45:51
*/
public interface IEmployeeService extends IService<Employee> {

    /**
     * 员工信息录入
     * @param employeeInfoDTO
     * @return
     */
    boolean informationEntry(EmployeeInfoDTO employeeInfoDTO);


    /**
     * 员工信息修改
     * @param employeeInfoDTO
     * @return
     */
    boolean informationModification(EmployeeInfoDTO employeeInfoDTO);

    /**
     * 员工信息查询,返回信息做分页处理
     * @param employeeQueryDTO
     * @return
     */
    PageResult informationQuery(EmployeeQueryDTO employeeQueryDTO);


}
