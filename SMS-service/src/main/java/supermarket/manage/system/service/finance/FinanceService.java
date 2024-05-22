package supermarket.manage.system.service.finance;


import com.baomidou.mybatisplus.extension.service.IService;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;

import java.util.List;
import java.util.Map;

/**
* @author ASUS
* @description 针对表【finance】的数据库操作Service
* @createDate 2024-05-07 00:19:04
*/
public interface FinanceService extends IService<Finance> {

    /**
     * 记录财务信息
     * @param finance
     * @return
     */
    boolean recordFinance(Finance finance);

    /**
     * 修改财务信息
     * @param financeInfoDTO
     * @return
     */
    boolean updateFinance(FinanceInfoDTO financeInfoDTO);

    /**
     * 删除财务信息
     * @param financeInfoDTO
     * @return
     */
    boolean deleteFinance(FinanceInfoDTO financeInfoDTO);

    /**
     * 查询所有财务信息
     * @param pageQueryDTO
     * @return
     */
    PageResult queryFinanceALL(PageQueryDTO pageQueryDTO);

    /**
     * 条件查询财务信息
     * @param pageQueryDTO
     * @return
     */
    PageResult queryFinance(PageQueryDTO pageQueryDTO);



    /**
     * 生成收入报表
     * @param financeInfoDTO
     * @return
     */
    List<Map<String, Object>> gengerateinFinance(FinanceInfoDTO financeInfoDTO);

    /**
     * 生成支出报表
     * @param financeInfoDTO
     * @return
     */
    List<Map<String, Object>> gengerateexFinance(FinanceInfoDTO financeInfoDTO);

    /**
     * 生成利润报表
     * @param financeInfoDTO
     * @return
     */
    List<Map<String, Object>> gengerateprFinance(FinanceInfoDTO financeInfoDTO);

}
