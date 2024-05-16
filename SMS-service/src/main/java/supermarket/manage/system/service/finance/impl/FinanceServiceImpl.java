package supermarket.manage.system.service.finance.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.model.domain.Employee;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.dto.FinanceInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.FinanceMapper;
import supermarket.manage.system.service.finance.FinanceService;
import supermarket.manage.system.service.finance.executor.GenerateFinanceExecutorConfig;
import supermarket.manage.system.service.finance.executor.IGenerateFinanceExecutor;
import supermarket.manage.system.service.support.CommonalitySupport;

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
                        .updateTime(new Date())
                        .createTime(financeInfoDTO.getCreateTime())
                        .isDeleted(1)
                        .build()
        );
    }

    @Override
    public PageResult queryFinanceALL(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Finance> page = financeMapper.selectPage(
                new Page<Finance>(pag, pagesize),
                new QueryWrapper<Finance>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED.getCode())
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

    @Override
    public PageResult queryFinance(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();
        if (null == pageQueryDTO.getKeyword()||null==pageQueryDTO.getKeywordType()) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_NOT_EXISTS));
        }
        //获取查询类型
        String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.FINANCE );
        if(null==queryTypeName){
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        }
        //0为未删除，1为已删除
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<Finance>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());
        //判断查询类型执行对应查询
        queryWrapper = queryWrapper.eq(queryTypeName, pageQueryDTO.getKeyword());
        Page<Finance> page = financeMapper.selectPage(
                new Page<Finance>(pag, pagesize),
                queryWrapper
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




