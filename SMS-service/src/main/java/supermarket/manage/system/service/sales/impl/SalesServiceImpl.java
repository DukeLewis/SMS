package supermarket.manage.system.service.sales.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.model.domain.Sales;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SalesInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.SalesMapper;
import supermarket.manage.system.service.sales.SalesService;
import supermarket.manage.system.service.support.CommonalitySupport;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author ASUS
* @description 针对表【sales】的数据库操作Service实现
* @createDate 2024-05-07 00:19:13
*/
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales>
    implements SalesService {

    @Resource
    private SalesMapper salesMapper;
    @Override
    public boolean informationEntry(SalesInfoDTO salesInfoDTO) {
        return save(Sales.builder()
                        .gName(salesInfoDTO.getGname())
                        .gPrice(salesInfoDTO.getGPrice())
                        .saleNum(salesInfoDTO.getSaleNum())
                        .saleTime(salesInfoDTO.getSaleTime())
                        .saler(salesInfoDTO.getSaler())
                        .updateTime(new Date())
                        .createTime(new Date())
                        .isDeleted(0)
                        .build());


    }

    @Override
    public boolean informationModification(SalesInfoDTO salesInfoDTO) {
        return updateById(Sales.builder()
                .gName(salesInfoDTO.getGname())
                .gPrice(salesInfoDTO.getGPrice())
                .saleNum(salesInfoDTO.getSaleNum())
                .saleTime(salesInfoDTO.getSaleTime())
                .saler(salesInfoDTO.getSaler())
                .updateTime(new Date())
                .build());

    }

    @Override
    public boolean informationDeletion(SalesInfoDTO salesInfoDTO) {
        return updateById(Sales.builder()
                .gName(salesInfoDTO.getGname())
                .gPrice(salesInfoDTO.getGPrice())
                .saleNum(salesInfoDTO.getSaleNum())
                .saleTime(salesInfoDTO.getSaleTime())
                .saler(salesInfoDTO.getSaler())
                .updateTime(new Date())
                .isDeleted(1)
                .build());
    }

    @Override
    public PageResult informationQuery(@NotNull PageQueryDTO pageQueryDTO){
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();
        if (null == pageQueryDTO.getKeyword()||null==pageQueryDTO.getKeywordType()) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_NOT_EXISTS));
        }
        //获取查询类型
        String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.GOODS);
        if(null==queryTypeName){
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        }
    }
}




