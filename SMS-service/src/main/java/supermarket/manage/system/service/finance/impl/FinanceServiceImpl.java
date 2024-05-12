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

        //todo
//        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("create_time,sum(revenue) as total_revenue").groupBy("create_time").orderByAsc("create_time");
//        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);

//        return mapList;

    }


    @Override
    public List<Map<String, Object>> gengerateexFinance(FinanceInfoDTO financeInfoDTO) {

        IGenerateFinanceExecutor generateFinanceExecutor = getGenerateFinanceExecutor(financeInfoDTO);

        return generateFinanceExecutor.generateDisburseFinance(financeInfoDTO);

        //todo
//        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("create_time,sum(spend) as total_spend,sum(costs) as total_costs,sum(water_cost) as total_water_cost,sum(ele_cost) as total_ele_cost")
//                .groupBy("create_time")
//                .orderByAsc("create_time");
//        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);
//        return mapList;

    }

    @Override
    public List<Map<String, Object>> gengerateprFinance(FinanceInfoDTO financeInfoDTO) {

        IGenerateFinanceExecutor generateFinanceExecutor = getGenerateFinanceExecutor(financeInfoDTO);

        return generateFinanceExecutor.generateProfitFinance(financeInfoDTO);


        //todo
//        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("create_time,sum(spend) as total_spend,sum(revenue) as total_revenue,total_spend-total_revenue as total_lirun")
//                .groupBy("create_time")
//                .orderByAsc("create_time");
//        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);
//        return mapList;

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




