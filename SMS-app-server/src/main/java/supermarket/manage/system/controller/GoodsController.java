package supermarket.manage.system.controller;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.EmployeeQueryDTO;
import supermarket.manage.system.model.dto.GoodsQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.employee.IEmployeeService;
import supermarket.manage.system.service.goods.IGoodsService;

import javax.annotation.Resource;

/**
 * @description: 商品管理模块
 * @author：dukelewis
 * @date: 2024/4/10
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@Api(tags = "2.商品管理模块")
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;


    @PostMapping("/enter")
    @ApiOperation("商品信息录入")
    public AppResult informationEntry(@NotNull @RequestBody GoodsInfoDTO goodsInfoDTO){
        return AppResult.success(goodsService.informationEntry(goodsInfoDTO));
    }

    @PostMapping("/modification")
    @ApiOperation("商品信息修改")
    public AppResult informationModification(@Validated @NotNull @RequestBody GoodsInfoDTO goodsInfoDTO){
        return AppResult.success(goodsService.informationModification(goodsInfoDTO));
    }

    @GetMapping("/query")
    @ApiOperation("商品信息查询")
    public AppResult<PageResult> informationQuery(@NotNull GoodsQueryDTO goodsQueryDTO){
        return AppResult.success(goodsService.informationQuery(goodsQueryDTO));
    }
}
