package supermarket.manage.system.service.sales.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.domain.Sales;
import supermarket.manage.system.model.domain.Supplier;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SalesInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.SalesMapper;
import supermarket.manage.system.service.sales.SalesService;
import supermarket.manage.system.service.support.CommonalitySupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author ASUS
* @description 针对表【sales】的数据库操作Service实现
* @createDate 2024-05-07 00:19:13
*/
@Service
@Slf4j
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
        System.out.println();
        return updateById(Sales.builder()
                .gId(salesInfoDTO.getGid())
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
                .gId(salesInfoDTO.getGid())
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
    public PageResult informationQueryALL(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Sales> page = salesMapper.selectPage(
                new Page<Sales>(pag, pagesize),
                new QueryWrapper<Sales>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED.getCode())
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

    @Override
    public PageResult informationQuery(@NotNull PageQueryDTO pageQueryDTO){
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        if (null == pageQueryDTO.getKeyword()||null==pageQueryDTO.getKeywordType()) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_NOT_EXISTS));
        }




        if(pageQueryDTO.getKeywordType().equals("time")){
            //获取查询类型
            String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.SALES);
            //log.info("查询类型：{}",queryTypeName);
            System.out.println("查询类型：{}"+queryTypeName);
            if(null==queryTypeName){
                throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
            }
            //0为未删除，1为已删除
            QueryWrapper<Sales> queryWrapper = new QueryWrapper<Sales>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());
            //判断查询类型执行对应查询
            //日期查询

            List<Sales> byTime = salesMapper.getByTime(pageQueryDTO.getKeyword());

            log.info("结果:{}",byTime);
            return new PageResult(
                    pag, pagesize, Long.valueOf(byTime.size()), byTime
            );
        }else if (pageQueryDTO.getKeywordType().equals("name")){
            //获取查询类型
            String queryTypeName2 = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.GOODS);
            //log.info("查询类型：{}",queryTypeName);
            System.out.println("查询类型：{}"+queryTypeName2);
            if(null==queryTypeName2){
                throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
            }
            //0为未删除，1为已删除
            QueryWrapper<Sales> queryWrapper2 = new QueryWrapper<Sales>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());

            queryWrapper2 = queryWrapper2.eq(queryTypeName2, pageQueryDTO.getKeyword());
            Page<Sales> page = salesMapper.selectPage(
                    new Page<Sales>(pag, pagesize),
                    queryWrapper2
            );
            return new PageResult(
                    pag, pagesize, page.getTotal(), page.getRecords()
            );
        }else{
            //获取查询类型
            String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.SALES);
            //log.info("查询类型：{}",queryTypeName);
            System.out.println("查询类型：{}"+queryTypeName);
            if(null==queryTypeName){
                throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
            }
            //0为未删除，1为已删除
            QueryWrapper<Sales> queryWrapper = new QueryWrapper<Sales>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());
            //判断查询类型执行对应查询
//            System.out.println(queryTypeName);
//            System.out.println(pageQueryDTO.getKeyword());
            queryWrapper = queryWrapper.eq(queryTypeName, pageQueryDTO.getKeyword());
            Page<Sales> page = salesMapper.selectPage(
                    new Page<Sales>(pag, pagesize),
                    queryWrapper
            );
            return new PageResult(
                    pag, pagesize, page.getTotal(), page.getRecords()
            );
        }


    }
}




