package supermarket.manage.system.service.finance.executor.impl;

import org.springframework.stereotype.Service;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.service.finance.executor.IGenerateFinanceExecutor;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
@Service("generateYearFinanceExecutor")
public class GenerateYearFinanceExecutor implements IGenerateFinanceExecutor {

    @Override
    public List<Map<String, Object>> gengerateIncomeFinance(FinanceInfoDTO financeInfoDTO) {
        return null;
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
