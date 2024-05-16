package supermarket.manage.system.service.goods.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supermarket.manage.system.common.annotation.InfoLog;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.InfoType;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.common.util.ListUtil;
import supermarket.manage.system.model.domain.Finance;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.domain.Inventory;
import supermarket.manage.system.model.domain.Supplier;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SupplierPageQueryDTO;
import supermarket.manage.system.model.entity.QuerySupplierListOfGoodsEntity;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.InventoryMapper;
import supermarket.manage.system.repository.mysql.mapper.SupplierMapper;
import supermarket.manage.system.service.support.CommonalitySupport;
import supermarket.manage.system.service.goods.IGoodsService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ASUS
 * @description 针对表【goods】的数据库操作Service实现
 * @createDate 2024-04-10 23:24:59
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods>
        implements IGoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @Resource
    private InventoryMapper inventoryMapper;

    //todo 考虑是否录入商品信息同时需要新增入库信息
    @Override
    @InfoLog(infoType = InfoType.ADD, item = "商品", infoItemIdExpression = "#goodsInfoDTO.gid")
    @Transactional(rollbackFor = Exception.class)
    public boolean informationEntry(GoodsInfoDTO goodsInfoDTO) {
        Date date = new Date();
        return save(Goods.builder()
                .gId(goodsInfoDTO.getGid())
                .gName(goodsInfoDTO.getGname())
                .purchasePrice(goodsInfoDTO.getPurchasePrice())
                .inventory(goodsInfoDTO.getInventory())
                .inventoryThreshold(goodsInfoDTO.getInventoryThreshold())
                .sellPrice(goodsInfoDTO.getSellPrice())
                .gBrand(goodsInfoDTO.getGbrand())
                .gCategory(goodsInfoDTO.getGcategory())
                .gSpecs(goodsInfoDTO.getGspecs())
                .gOrigin(goodsInfoDTO.getGorigin())
                .gType(goodsInfoDTO.getGtype())
                .supplierIdList(ListUtil.list2String(goodsInfoDTO.getSupplierIdList()))
                .supplierPriceList(ListUtil.list2String(goodsInfoDTO.getSupplierPriceList()))
                .createTime(date)
                .updateTime(date)
                .isDeleted(DeletedType.UN_DELETED.getCode())
                .build())
                &&
                inventoryMapper.insert(
                        Inventory.builder()
                                .gId(goodsInfoDTO.getGid())
                                .gName(goodsInfoDTO.getGname())
                                .gCategory(goodsInfoDTO.getGcategory())
                                .inboundNum(goodsInfoDTO.getInventory())
                                .inboundTime(date)
                                .supplier(null)
                                .outboundNum(0)
                                .outboundTime(date)
                                .purpose("录入商品信息")
                                .createTime(date)
                                .updateTime(date)
                                .isDeleted(DeletedType.UN_DELETED.getCode()).build()
                ) > 0;
    }

    //todo 该处不可更改库存
    @Override
    public boolean informationModification(GoodsInfoDTO goodsInfoDTO) {
        Goods goods = getById(goodsInfoDTO.getGid());

        if (null == goods || DeletedType.DELETED.getCode().equals(goods.getIsDeleted())) {
            throw new ApplicationException(AppResult.failed(ResultCode.GOODS_NOT_EXISTS));
        }

        return updateById(Goods.builder()
                .gId(goodsInfoDTO.getGid())
                .gName(goodsInfoDTO.getGname())
                .purchasePrice(goodsInfoDTO.getPurchasePrice())
                .inventoryThreshold(goodsInfoDTO.getInventoryThreshold())
                .sellPrice(goodsInfoDTO.getSellPrice())
                .gBrand(goodsInfoDTO.getGbrand())
                .gCategory(goodsInfoDTO.getGcategory())
                .gSpecs(goodsInfoDTO.getGspecs())
                .gOrigin(goodsInfoDTO.getGorigin())
                .gType(goodsInfoDTO.getGtype())
                .supplierIdList(ListUtil.list2String(goodsInfoDTO.getSupplierIdList()))
                .supplierPriceList(ListUtil.list2String(goodsInfoDTO.getSupplierPriceList()))
                .isDeleted(goodsInfoDTO.getIsDeleted())
                .build());
    }

    @Override
    public PageResult informationQueryALL(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Goods> page = goodsMapper.selectPage(
                new Page<Goods>(pag, pagesize),
                new QueryWrapper<Goods>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED.getCode())
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }


    @Override
    public PageResult informationQuery(PageQueryDTO pageQueryDTO) {
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
        //0为未删除，1为已删除
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());
        //判断查询类型执行对应查询
        queryWrapper = queryWrapper.eq(queryTypeName, pageQueryDTO.getKeyword());
        Page<Goods> page = goodsMapper.selectPage(
                new Page<Goods>(pag, pagesize),
                queryWrapper
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

}




