package supermarket.manage.system.service.finance.executor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
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
public class GenerateDayFinanceExecutor implements IGenerateFinanceExecutor {

    @Resource
    private FinanceMapper financeMapper;


    @Override
    public List<Map<String, Object>> gengerateIncomeFinance(FinanceInfoDTO financeInfoDTO) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("create_time,sum(revenue) as total_revenue").groupBy("create_time").orderByAsc("create_time");
        List<Map<String, Object>> mapList = financeMapper.selectMaps(queryWrapper);

        return mapList;
    }

    @Override
    public List<Map<String, Object>> gengerateDisburseFinance(FinanceInfoDTO financeInfoDTO) {
        return null;
    }

    @Override
    public List<Map<String, Object>> gengerateProfitFinance(FinanceInfoDTO financeInfoDTO) {
        return null;
    }
}
