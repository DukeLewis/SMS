package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.EmployeeInfoDTO;
import supermarket.manage.system.model.dto.EmployeeQueryDTO;
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
        return AppResult.success("录入成功");
    }

    @PostMapping("/modification")
    @ApiOperation("员工信息修改")
    public AppResult informationModification(@NotNull @RequestBody EmployeeInfoDTO employeeInfoDTO){
        return AppResult.success("修改成功");
    }

    @GetMapping("/query")
    @ApiOperation("员工信息查询")
    //todo 需要封装vo返回，考虑是否做分页
    public AppResult informationQuery(@NotNull @RequestBody EmployeeQueryDTO employeeQueryDTO){
        return AppResult.success();
    }

}
