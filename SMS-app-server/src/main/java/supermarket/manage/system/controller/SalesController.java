package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SalesInfoDTO;
import supermarket.manage.system.model.dto.SupplierInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.sales.SalesService;
import supermarket.manage.system.service.supplier.SupplierService;

import javax.annotation.Resource;

@RestController
@Api(tags = "8.销售信息管理模块")
@RequestMapping("/sales")
public class SalesController {
    @Resource
    private SalesService salesService;
    @PostMapping("/enter")
    @ApiOperation("销售信息录入")
    public AppResult informationEntry(@NotNull @RequestBody SalesInfoDTO salesInfoDTO){
        return AppResult.success(salesService.informationEntry(salesInfoDTO));
    }
    @PostMapping("/modification")
    @ApiOperation("销售信息修改")
    public AppResult informationModification(@Validated @NotNull @RequestBody SalesInfoDTO salesInfoDTO){
        return AppResult.success(salesService.informationModification(salesInfoDTO));
    }

    @PostMapping("/Deletion")
    @ApiOperation("销售信息删除")
    public AppResult informationDeletion(@Validated @NotNull @RequestBody SalesInfoDTO salesInfoDTO){
        return AppResult.success(salesService.informationDeletion(salesInfoDTO));
    }
    @GetMapping("/queryALL")
    @ApiOperation("所有销售信息查询")
    public AppResult<PageResult> informationQueryALL(@NotNull PageQueryDTO pageQueryDTO){
        return AppResult.success(salesService.informationQueryALL(pageQueryDTO));
    }

    @GetMapping("/query")
    @ApiOperation("销售信息查询")
    public AppResult<PageResult> informationQuery(@NotNull PageQueryDTO pageQueryDTO){
        return AppResult.success(salesService.informationQuery(pageQueryDTO));
    }

}

