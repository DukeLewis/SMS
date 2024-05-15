package supermarket.manage.system.service.employee.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.model.domain.Employee;
import supermarket.manage.system.model.domain.Restock;
import supermarket.manage.system.model.dto.EmployeeInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.EmployeeMapper;
import supermarket.manage.system.service.employee.IEmployeeService;
import supermarket.manage.system.service.support.CommonalitySupport;

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
        Employee employee = getById(employeeInfoDTO.getEid());

        if(null==employee||DeletedType.DELETED.getCode().equals(employee.getIsDeleted())){
            throw new ApplicationException(AppResult.failed(ResultCode.ERROR_IS_NULL));
        }

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
    public PageResult queryEmployeeAll(PageQueryDTO pageQueryDTO){
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Employee> page = employeeMapper.selectPage(
                new Page<Employee>(pag, pagesize),
                new QueryWrapper<Employee>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED.getCode())
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

    @Override
    public PageResult informationQuery(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        if (null == pageQueryDTO.getKeyword()||null==pageQueryDTO.getKeywordType()) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_NOT_EXISTS));
        }

        //获取查询类型
        String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.EMPLOYEE);
        System.out.println(queryTypeName+"111111111111111");
        if(null==queryTypeName){
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        }

        Page<Employee> page = employeeMapper.selectPage(
                new Page<Employee>(pag,pagesize),
                new QueryWrapper<Employee>().eq(queryTypeName, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED.getCode())
        );
        return new PageResult(
                pag,pagesize,page.getTotal(),page.getRecords()
        );
    }
}




