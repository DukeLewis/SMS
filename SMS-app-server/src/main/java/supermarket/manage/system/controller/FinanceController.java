package supermarket.manage.system.controller;



import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.dto.AuthDTO;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.RestockInfoDTO;
import supermarket.manage.system.model.vo.AuthVO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.service.auth.IAuthService;
import supermarket.manage.system.service.finance.FinanceService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
@RestController
@Api(tags = "6.财务管理模块")
@RequestMapping("/finance")
public class FinanceController {

    @Resource
    private FinanceService financeService;

    @ApiOperation("记录财务信息")
    @PostMapping("/record")
    public AppResult recordFinance(@NotNull @Validated @RequestBody Finance finance) {
        return AppResult.success(financeService.recordFinance(finance));
    }
    @ApiOperation("修改财务信息")
    @PostMapping("/update")
    public AppResult updateFinance(@NotNull @Validated @RequestBody FinanceInfoDTO FinanceInfoDTO) {
        return AppResult.success(financeService.updateFinance(FinanceInfoDTO));
    }
    @ApiOperation("删除财务信息")
    @PostMapping("/delete")
    public AppResult deleteFinance(@NotNull @Validated @RequestBody FinanceInfoDTO FinanceInfoDTO) {
        return AppResult.success(financeService.deleteFinance(FinanceInfoDTO));
    }

    @ApiOperation("查询所有财务信息")
    @GetMapping("/queryall")
    public AppResult<PageResult> queryFinanceALL(@NotNull PageQueryDTO PageQueryDTO) {
        return AppResult.success(financeService.queryFinanceALL(PageQueryDTO));
    }

    @ApiOperation("财务信息条件查询")
    @GetMapping("/query")
    public AppResult<PageResult> queryFinance(@NotNull PageQueryDTO PageQueryDTO) {
        return AppResult.success(financeService.queryFinance(PageQueryDTO));
    }


    @ApiOperation("生成收入报表")
    @PostMapping("/gengeratein")
    public AppResult gengerateinFinance(@Validated @RequestBody FinanceInfoDTO FinanceInfoDTO) {
        return AppResult.success(financeService.gengerateinFinance(FinanceInfoDTO));
    }
    @ApiOperation("生成支出报表")
    @PostMapping("/gengerateex")
    public AppResult gengerateexFinance(@Validated @RequestBody FinanceInfoDTO FinanceInfoDTO) {
        return AppResult.success(financeService.gengerateexFinance(FinanceInfoDTO));
    }
    @ApiOperation("生成利润报表")
    @PostMapping("/gengeratepr")
    public AppResult gengerateprFinance(@Validated @RequestBody FinanceInfoDTO FinanceInfoDTO) {
        return AppResult.success(financeService.gengerateprFinance(FinanceInfoDTO));
    }

}
