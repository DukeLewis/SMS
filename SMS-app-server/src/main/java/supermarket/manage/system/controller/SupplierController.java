package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SupplierInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.supplier.SupplierService;

import javax.annotation.Resource;

@RestController
@Api(tags = "7.供应商管理模块")
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierService supplierService;
    @PostMapping("/enter")
    @ApiOperation("供应商信息录入")
    public AppResult informationEntry(@NotNull @RequestBody SupplierInfoDTO supplierInfoDTO){
        return AppResult.success(supplierService.informationEntry(supplierInfoDTO));
    }
    @PostMapping("/modification")
    @ApiOperation("供应商信息修改")
    public AppResult informationModification(@Validated @NotNull @RequestBody SupplierInfoDTO supplierInfoDTO){
        return AppResult.success(supplierService.informationModification(supplierInfoDTO));
    }

    @GetMapping("/query")
    @ApiOperation("供应商信息查询")
    public AppResult<PageResult> informationQuery(@NotNull PageQueryDTO pageQueryDTO){
        return AppResult.success(supplierService.informationQuery(pageQueryDTO));
    }

}
