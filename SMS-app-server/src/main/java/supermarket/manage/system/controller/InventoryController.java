package supermarket.manage.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.annotation.RateLimiter;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.InventoryInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.inventory.IInventoryService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/15
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/inventory")
@Api(tags = "4.库存管理模块")
public class InventoryController {

    @Resource
    private IInventoryService inventoryService;

    @PostMapping("/add")
    @ApiOperation("商品入库记录")
    @RateLimiter(value = "supermarket.manage.system.controller.InventoryController.addInventory"
    ,limit = "100", windowSize = "1",msg = "库存添加频率过高")
    public AppResult addInventory(@RequestBody @NotNull @Validated InventoryInfoDTO inventoryInfoDTO) {
        return AppResult.success(inventoryService.addInventory(inventoryInfoDTO));
    }

    @PostMapping("/del")
    @ApiOperation("商品出库记录")
    public AppResult delInventory(@RequestBody @NotNull @Validated InventoryInfoDTO inventoryInfoDTO) {
        return AppResult.success(inventoryService.delInventory(inventoryInfoDTO));
    }


    @GetMapping("/query")
    @ApiOperation("单个商品库存查询")
    public AppResult<PageResult> queryInventory(@NotNull PageQueryDTO pageQueryDTO) {
        return AppResult.success(inventoryService.queryInventory(pageQueryDTO));
    }

    @GetMapping("/queryall")
    @ApiOperation("所有商品库存查询")
    public AppResult<PageResult> queryInventoryAll(@NotNull PageQueryDTO pageQueryDTO) {
        return AppResult.success(inventoryService.queryInventoryAll(pageQueryDTO));
    }



}
