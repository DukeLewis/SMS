package supermarket.manage.system.service.finance.executor.impl;

import org.springframework.stereotype.Service;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.service.finance.executor.GenerateFinanceExecutorConfig;
import supermarket.manage.system.service.finance.executor.IGenerateFinanceExecutor;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
@Service("generateMonthFinanceExecutor")
public class GenerateMonthFinanceExecutor extends GenerateFinanceExecutorConfig implements IGenerateFinanceExecutor {

    @Override
    public List<Map<String, Object>> generateIncomeFinance(FinanceInfoDTO financeInfoDTO) {
        return null;
    }

    @Override
    public List<Map<String, Object>> generateDisburseFinance(FinanceInfoDTO financeInfoDTO) {
        return null;
    }

    @Override
    public List<Map<String, Object>> generateProfitFinance(FinanceInfoDTO financeInfoDTO) {
        return null;
    }
}
