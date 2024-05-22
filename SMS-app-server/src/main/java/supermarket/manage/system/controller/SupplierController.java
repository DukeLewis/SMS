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
import supermarket.manage.system.model.dto.SupplierPageQueryDTO;
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

    @PostMapping("/deletion")
    @ApiOperation("供应商信息删除")
    public AppResult informationDeletion(@Validated @NotNull @RequestBody SupplierInfoDTO supplierInfoDTO){
        return AppResult.success(supplierService.informationDeletion(supplierInfoDTO));
    }

    @GetMapping("/queryALL")
    @ApiOperation("所有供应商信息查询")
    public AppResult<PageResult> informationQueryALL(@NotNull PageQueryDTO pageQueryDTO){
        return AppResult.success(supplierService.informationQueryALL(pageQueryDTO));
    }

    @GetMapping("/query")
    @ApiOperation("供应商信息条件查询")
    public AppResult<PageResult> informationQuery(@NotNull PageQueryDTO pageQueryDTO){
        return AppResult.success(supplierService.informationQuery(pageQueryDTO));
    }

    @GetMapping("/querySupplier")
    @ApiOperation("根据商品名称的供应商信息查询")
    public AppResult<PageResult> querySupplier(@NotNull @Validated SupplierPageQueryDTO supplierPageQueryDTO){
        return AppResult.success(supplierService.querySupplier(supplierPageQueryDTO));
    }

}
