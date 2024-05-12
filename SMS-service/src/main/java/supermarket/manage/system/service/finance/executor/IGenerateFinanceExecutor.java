package supermarket.manage.system.service.finance.executor;

import supermarket.manage.system.model.dto.FinanceInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
public interface IGenerateFinanceExecutor {
    /**
     * 生成支出报表
     * @param financeInfoDTO
     * @return
     */
    List<Map<String, Object>> generateDisburseFinance(FinanceInfoDTO financeInfoDTO);

    /**
     * 获取收入报表
     * @param financeInfoDTO 报表信息
     * @return 报表信息
     */
    List<Map<String, Object>> generateIncomeFinance(FinanceInfoDTO financeInfoDTO);

    /**
     * 获取利润报表
     * @param financeInfoDTO
     * @return
     */
    List<Map<String, Object>> generateProfitFinance(FinanceInfoDTO financeInfoDTO);
}
