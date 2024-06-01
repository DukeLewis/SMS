package supermarket.manage.system.service.finance.executor;

import org.springframework.stereotype.Component;
import supermarket.manage.system.common.commons.enumeration.FinanceType;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
import supermarket.manage.system.service.finance.executor.impl.GenerateDayFinanceExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/5/12
 * @Copyright： https://github.com/DukeLewis
 */
public class GenerateFinanceExecutorConfig {

    public static final Map<String,IGenerateFinanceExecutor> generateFinanceExecutorMap = new HashMap<>();

    @Resource
    protected FinanceMapper financeMapper;


    @Resource
    private IGenerateFinanceExecutor generateDayFinanceExecutor;


    @Resource
    private IGenerateFinanceExecutor generateMonthFinanceExecutor;

    @Resource
    private IGenerateFinanceExecutor generateYearFinanceExecutor;

    @PostConstruct
    public void init(){
//        generateDayFinanceExecutor.generateIncomeFinance(null);
        generateFinanceExecutorMap.put(FinanceType.DAY.getInfo(),generateDayFinanceExecutor);
        generateFinanceExecutorMap.put(FinanceType.MONTH.getInfo(),generateMonthFinanceExecutor);
        generateFinanceExecutorMap.put(FinanceType.YEAR.getInfo(),generateYearFinanceExecutor);
    }

}
