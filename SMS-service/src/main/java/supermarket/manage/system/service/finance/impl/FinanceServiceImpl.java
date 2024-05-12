package supermarket.manage.system.service.finance.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
import supermarket.manage.system.service.finance.FinanceService;
import supermarket.manage.system.service.finance.executor.GenerateFinanceExecutorConfig;
import supermarket.manage.system.service.finance.executor.IGenerateFinanceExecutor;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author ASUS
* @description 针对表【finance】的数据库操作Service实现
* @createDate 2024-05-07 00:19:04
*/
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance>
    implements FinanceService {

    @Resource
    FinanceMapper financeMapper;

    @Override
    public boolean recordFinance(FinanceInfoDTO financeInfoDTO) {
        return save(Finance.builder().recordTime(financeInfoDTO.getRecordTime())
                .revenue( Double.parseDouble(financeInfoDTO.getRevenue()))
                .costs(Double.parseDouble(financeInfoDTO.getCosts()))
                .waterCost(Double.parseDouble(financeInfoDTO.getWaterCost()))
                .eleCost(Double.parseDouble(financeInfoDTO.getEleCost()))
                .spend(Double.parseDouble(financeInfoDTO.getSpend()))
                .updateTime(financeInfoDTO.getUpdateTime())
                .createTime(financeInfoDTO.getCreateTime())
                .isDeleted(0).build());
    }

    @Override
    public boolean updateFinance(FinanceInfoDTO financeInfoDTO) {
        return updateById(
                Finance.builder()
                        .fId(financeInfoDTO.getFid())
                        .recordTime(financeInfoDTO.getRecordTime())
                        .revenue( Double.parseDouble(financeInfoDTO.getRevenue()))
                        .costs(Double.parseDouble(financeInfoDTO.getCosts()))
                        .waterCost(Double.parseDouble(financeInfoDTO.getWaterCost()))
                        .eleCost(Double.parseDouble(financeInfoDTO.getEleCost()))
                        .spend(Double.parseDouble(financeInfoDTO.getSpend()))
                        .updateTime(new Date())
                        .createTime(financeInfoDTO.getCreateTime())
                        .isDeleted(financeInfoDTO.getIsDeleted())
                        .build()
        );

    }

    @Override
    public boolean deleteFinance(FinanceInfoDTO financeInfoDTO) {
        return updateById(
                Finance.builder()
                        .fId(financeInfoDTO.getFid())
                        .recordTime(financeInfoDTO.getRecordTime())
                        .revenue( Double.parseDouble(financeInfoDTO.getRevenue()))
                        .costs(Double.parseDouble(financeInfoDTO.getCosts()))
                        .waterCost(Double.parseDouble(financeInfoDTO.getWaterCost()))
                        .eleCost(Double.parseDouble(financeInfoDTO.getEleCost()))
                        .spend(Double.parseDouble(financeInfoDTO.getSpend()))
                        .updateTime(new Date())
                        .createTime(financeInfoDTO.getCreateTime())
                        .isDeleted(1)
                        .build()
        );
    }

    @Override
    public PageResult queryFinance(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();
        Page<Finance> page = financeMapper.selectPage(
                new Page<Finance>(pag, pagesize),
                new QueryWrapper<Finance>().eq(Constant.CREATE_DATE, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, 1)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }


    @Override
    public List<Map<String, Object>> gengerateinFinance(FinanceInfoDTO financeInfoDTO) {

        IGenerateFinanceExecutor generateFinanceExecutor = getGenerateFinanceExecutor(financeInfoDTO);

        return generateFinanceExecutor.generateIncomeFinance(financeInfoDTO);
    }


    @Override
    public List<Map<String, Object>> gengerateexFinance(FinanceInfoDTO financeInfoDTO) {

        IGenerateFinanceExecutor generateFinanceExecutor = getGenerateFinanceExecutor(financeInfoDTO);

        return generateFinanceExecutor.generateDisburseFinance(financeInfoDTO);

    }

    @Override
    public List<Map<String, Object>> gengerateprFinance(FinanceInfoDTO financeInfoDTO) {

        IGenerateFinanceExecutor generateFinanceExecutor = getGenerateFinanceExecutor(financeInfoDTO);

        return generateFinanceExecutor.generateProfitFinance(financeInfoDTO);

    }

    /**
     * 获取对应的财务报表执行器
     * @param financeInfoDTO
     * @return
     */
    private static IGenerateFinanceExecutor getGenerateFinanceExecutor(FinanceInfoDTO financeInfoDTO) {
        String timeType = financeInfoDTO.getTimeType();

        IGenerateFinanceExecutor generateFinanceExecutor =
                GenerateFinanceExecutorConfig.generateFinanceExecutorMap.get(timeType);

        if(null==generateFinanceExecutor){
            throw new ApplicationException(AppResult.failed(ResultCode.TIME_TYPE_NOT_EXISTS));
        }
        return generateFinanceExecutor;
    }
}




