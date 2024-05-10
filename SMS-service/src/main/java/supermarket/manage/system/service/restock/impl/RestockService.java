package supermarket.manage.system.service.restock.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.RestockStatus;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.common.util.ListUtil;
import supermarket.manage.system.model.domain.Restock;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.RestockInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.RestockMapper;
import supermarket.manage.system.service.restock.IRestockService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author ASUS
* @description 针对表【restock】的数据库操作Service实现
* @createDate 2024-04-21 14:58:04
*/
@Service
public class RestockService extends ServiceImpl<RestockMapper, Restock>
   implements IRestockService {

    @Resource
    RestockMapper restockMapper;


    @Override
    public boolean addRestock(RestockInfoDTO restockInfoDTO) {
        return save(Restock.builder()
                .productIdList(ListUtil.list2String(restockInfoDTO.getProductIdList()))
                .supplierList(ListUtil.list2String(restockInfoDTO.getSupplierList()))
                .productPricelist(ListUtil.list2String(restockInfoDTO.getProductPricelist()))
                .arriveTime(restockInfoDTO.getArriveTime())
                .status(RestockStatus.UN_ARRIVED.getStatus())
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(0).build());
    }

    @Override
    public boolean updateRestock(RestockInfoDTO restockInfoDTO) {
        Restock restock = getById(restockInfoDTO.getRId());
        if(null==restock||DeletedType.UN_DELETED.getCode().equals(restock.getIsDeleted())){
            throw new ApplicationException(AppResult.failed(ResultCode.RESTOCK_NOT_EXISTS));
        }
        return updateById(
                Restock.builder()
                        .rId(restockInfoDTO.getRId())
                        .productIdList(ListUtil.list2String(restockInfoDTO.getProductIdList()))
                        .supplierList(ListUtil.list2String(restockInfoDTO.getSupplierList()))
                        .productPricelist(ListUtil.list2String(restockInfoDTO.getProductPricelist()))
                        .arriveTime(restockInfoDTO.getArriveTime())
                        .status(restockInfoDTO.getStatus())
                        //注意，如果这里进行了更改那么就是删除该记录
                        .isDeleted(restockInfoDTO.getIsDeleted())
                        .updateTime(new Date())
                        .build()
        );
    }

    @Override
    public PageResult queryRestockall(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Restock> page = restockMapper.selectPage(
                new Page<Restock>(pag, pagesize),
                new QueryWrapper<Restock>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }


    @Override
    public PageResult queryRestock(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Restock> page = restockMapper.selectPage(
                new Page<Restock>(pag, pagesize),
                new QueryWrapper<Restock>().eq(Constant.RESTOCK_ID, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, 1)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

}




