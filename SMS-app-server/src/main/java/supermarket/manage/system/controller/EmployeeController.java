package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.EmployeeInfoDTO;
import supermarket.manage.system.model.dto.EmployeeQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.employee.IEmployeeService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/8
 * @Copyright： https://github.com/DukeLewis
 */
@Api(tags = "3.员工管理模块")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;


    @PostMapping("/enter")
    @ApiOperation("员工信息录入")
    public AppResult informationEntry(@NotNull @RequestBody EmployeeInfoDTO employeeInfoDTO){
        return AppResult.success(employeeService.informationEntry(employeeInfoDTO)==true?"录入成功":"录入失败");
    }

    @PostMapping("/modification")
    @ApiOperation("员工信息修改")
    public AppResult informationModification(@Validated @NotNull @RequestBody EmployeeInfoDTO employeeInfoDTO){
        return AppResult.success(employeeService.informationModification(employeeInfoDTO)==true?"修改成功":"修改失败");
    }

    @GetMapping("/query")
    @ApiOperation("员工信息查询")
    public AppResult<PageResult> informationQuery(@NotNull EmployeeQueryDTO employeeQueryDTO){
        return AppResult.success(employeeService.informationQuery(employeeQueryDTO));
    }

}
