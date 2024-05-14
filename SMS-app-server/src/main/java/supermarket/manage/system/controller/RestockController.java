package supermarket.manage.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.RestockInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.restock.IRestockService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/21
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("restock")
@Api(tags = "5.进货管理")
public class RestockController {


    @Resource
    private IRestockService restockService;


    @ApiOperation("添加进货单")
    @PostMapping("/add")
    public AppResult addRestock(@Validated @RequestBody RestockInfoDTO restockInfoDTO) {
        return AppResult.success(restockService.addRestock(restockInfoDTO));
    }

    @ApiOperation("修改进货单")
    @PostMapping("/update")
    public AppResult updateRestock(@Validated @RequestBody RestockInfoDTO restockInfoDTO) {
        return AppResult.success(restockService.updateRestock(restockInfoDTO));
    }


    @ApiOperation("查询所有进货单")
    @PostMapping("/queryALL")
    public AppResult<PageResult> queryRestockALL(@NotNull PageQueryDTO pageQueryDTO) {
        return AppResult.success(restockService.queryRestockALL(pageQueryDTO));
    }

    @ApiOperation("查询某个进货单")
    @PostMapping("/query")
    public AppResult<PageResult> queryRestock(@NotNull PageQueryDTO pageQueryDTO) {
        return AppResult.success(restockService.queryRestock(pageQueryDTO));
    }


}
