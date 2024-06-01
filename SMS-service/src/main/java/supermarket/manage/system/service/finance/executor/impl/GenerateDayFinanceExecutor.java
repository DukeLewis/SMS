package supermarket.manage.system.service.finance.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
import supermarket.manage.system.service.finance.executor.GenerateFinanceExecutorConfig;
import supermarket.manage.system.service.finance.executor.IGenerateFinanceExecutor;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
@Service("generateDayFinanceExecutor")
public class GenerateDayFinanceExecutor extends GenerateFinanceExecutorConfig implements IGenerateFinanceExecutor {



    @Override
    public List<Map<String, Object>> generateIncomeFinance(FinanceInfoDTO financeInfoDTO) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("create_time,sum(amount) as total_revenue").groupBy("create_time").orderByAsc("create_time");
        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);

        return mapList;
    }

    @Override
    public List<Map<String, Object>> generateDisburseFinance(FinanceInfoDTO financeInfoDTO) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("create_time,sum(spend) as total_spend,sum(costs) as total_costs,sum(water_cost) as total_water_cost,sum(ele_cost) as total_ele_cost")
                .groupBy("create_time")
                .orderByAsc("create_time");
        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> generateProfitFinance(FinanceInfoDTO financeInfoDTO) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("create_time,sum(spend) as total_spend,sum(revenue) as total_revenue,total_spend-total_revenue as total_lirun")
                .groupBy("create_time")
                .orderByAsc("create_time");
        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);
        return mapList;
    }
}
