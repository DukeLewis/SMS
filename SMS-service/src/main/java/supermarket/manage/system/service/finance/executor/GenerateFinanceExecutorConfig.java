package supermarket.manage.system.service.finance.executor;

import org.springframework.stereotype.Component;
import supermarket.manage.system.common.commons.enumeration.FinanceType;

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
@Component
public class GenerateFinanceExecutorConfig {

    public static final Map<FinanceType,IGenerateFinanceExecutor> generateFinanceExecutorMap = new HashMap<>();


    @Resource
    private IGenerateFinanceExecutor generateDayFinanceExecutor;

    @Resource
    private IGenerateFinanceExecutor generateMonthFinanceExecutor;

    @Resource
    private IGenerateFinanceExecutor generateYearFinanceExecutor;

    @PostConstruct
    public void init(){
        generateFinanceExecutorMap.put(FinanceType.DAY,generateDayFinanceExecutor);
        generateFinanceExecutorMap.put(FinanceType.MONTH,generateMonthFinanceExecutor);
        generateFinanceExecutorMap.put(FinanceType.YEAR,generateYearFinanceExecutor);
    }

}
