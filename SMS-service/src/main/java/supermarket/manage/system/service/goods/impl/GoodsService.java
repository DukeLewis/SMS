package supermarket.manage.system.service.goods.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supermarket.manage.system.common.annotation.InfoLog;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.InfoType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.common.util.ListUtil;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.domain.Supplier;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.SupplierPageQueryDTO;
import supermarket.manage.system.model.entity.QuerySupplierListOfGoodsEntity;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.SupplierMapper;
import supermarket.manage.system.service.goods.GoodsSupport;
import supermarket.manage.system.service.goods.IGoodsService;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private GoodsSupport goodsSupport;

    //todo 录入商品信息同时需要新增入库信息
    @Override
    @InfoLog(infoType = InfoType.ADD, item = "商品", infoItemIdExpression = "#goodsInfoDTO.gid")
    public boolean informationEntry(GoodsInfoDTO goodsInfoDTO) {
        return save(Goods.builder()
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
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(0)
                .build());
    }

    //todo 该处不可更改库存
    @Override
    public boolean informationModification(GoodsInfoDTO goodsInfoDTO) {
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
                .build());
    }


    @Override
    public PageResult informationQuery(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Goods> page = goodsMapper.selectPage(
                new Page<Goods>(pag, pagesize),
                new QueryWrapper<Goods>().eq(Constant.GOODS_NAME, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult querySupplier(SupplierPageQueryDTO supplierPageQueryDTO) {
        Integer pag = supplierPageQueryDTO.getPage();
        Integer pagesize = supplierPageQueryDTO.getPagesize();

        GoodsSupport.SortType sortType = goodsSupport.sortTypeMap.get(supplierPageQueryDTO.getSortType());
        String sortKey = supplierPageQueryDTO.getSortKey();


        Goods goods = getById(supplierPageQueryDTO.getId());
        if(null==goods){
            throw new ApplicationException(ResultCode.GOODS_NOT_EXISTS.getMessage());
        }
        String[] supplierIdList = goods.getSupplierIdList().split(Constant.DATABASE_SPLIT);
        Page<Supplier> supplierList = supplierMapper.selectPage(
                new Page<Supplier>(pag, pagesize),
                (QueryWrapper<Supplier>)sortType.sortBy(new QueryWrapper<Supplier>()
                        .in(Constant.SUPPLIER_ID, supplierIdList),sortKey)
        );
        //创建供应商信息列表
        List<QuerySupplierListOfGoodsEntity> supplierListOfGoods = new ArrayList<>();
        List<Supplier> supplierListRecords = supplierList.getRecords();
        for (Supplier supplier : supplierListRecords) {
            supplierListOfGoods.add(new QuerySupplierListOfGoodsEntity(
                    supplier.getSId(),
                    supplier.getSName(),
                    supplier.getSPrincipal(),
                    supplier.getSPhone(),
                    supplier.getSAddress()
            ));
        }
        return new PageResult(
                supplierPageQueryDTO.getPage(),
                supplierPageQueryDTO.getPagesize(),
                supplierList.getTotal(),
                supplierListOfGoods
        );
    }
}




