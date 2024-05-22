package supermarket.manage.system.service.supplier.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import supermarket.manage.system.model.dto.SupplierInfoDTO;
import supermarket.manage.system.model.dto.SupplierPageQueryDTO;
import supermarket.manage.system.model.entity.QuerySupplierListOfGoodsEntity;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.SupplierMapper;
import supermarket.manage.system.service.supplier.SupplierService;
import supermarket.manage.system.service.support.CommonalitySupport;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author ASUS
* @description 针对表【supplier】的数据库操作Service实现
* @createDate 2024-05-07 00:19:18
*/
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
    implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public boolean informationEntry(SupplierInfoDTO supplierInfoDTO) {
        return save(Supplier.builder()
                .sName(supplierInfoDTO.getSname())
                .sPrincipal(supplierInfoDTO.getSprincipal())
                .sPhone(supplierInfoDTO.getSphone())
                .sAddress(supplierInfoDTO.getSaddress())
                .updateTime(new Date())
                .createTime(new Date())
                .isDeleted(0)
                .build());
    }

    @Override
    public boolean informationModification(SupplierInfoDTO supplierInfoDTO) {
        return updateById(Supplier.builder()
                .sId(supplierInfoDTO.getSid())
                .sName(supplierInfoDTO.getSname())
                .sPrincipal(supplierInfoDTO.getSprincipal())
                .sPhone(supplierInfoDTO.getSphone())
                .sAddress(supplierInfoDTO.getSaddress())
                .updateTime(new Date())
                .build());
    }

    @Override
    public boolean informationDeletion(SupplierInfoDTO supplierInfoDTO) {
        return updateById(Supplier.builder()
                .sId(supplierInfoDTO.getSid())
                .sName(supplierInfoDTO.getSname())
                .sPrincipal(supplierInfoDTO.getSprincipal())
                .sPhone(supplierInfoDTO.getSphone())
                .sAddress(supplierInfoDTO.getSaddress())
                .updateTime(new Date())
                .isDeleted(1)
                .build());
    }

    @Override
    public PageResult informationQueryALL(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Supplier> page = supplierMapper.selectPage(
                new Page<Supplier>(pag, pagesize),
                new QueryWrapper<Supplier>()
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
        System.out.println("=============");
        String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.SUPPLIER);
        System.out.println("1111111111111111");
        if(null==queryTypeName){
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        }
        //0为未删除，1为已删除
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<Supplier>().ne(Constant.IS_DELETED, DeletedType.DELETED.getCode());
        //判断查询类型执行对应查询
        queryWrapper = queryWrapper.eq(queryTypeName, pageQueryDTO.getKeyword());
        Page<Supplier> page = supplierMapper.selectPage(
                new Page<Supplier>(pag, pagesize),
                queryWrapper
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

        //获取商品信息
        Goods goods = goodsMapper.selectById(supplierPageQueryDTO.getId());
        if (null == goods) {
            throw new ApplicationException(AppResult.failed(ResultCode.GOODS_NOT_EXISTS));
        }
        //获取供应商id列表
        String[] supplierIdList = goods.getSupplierIdList().split(Constant.DATABASE_SPLIT);
        //获取供应商价格列表
        String[] supplierPriceList = goods.getSupplierPriceList().split(Constant.DATABASE_SPLIT);
        HashMap<String, Integer> supplierMap = new HashMap<>();
        int len = supplierIdList.length;
        for (int i = 0; i < len; i++) {
            supplierMap.put(supplierIdList[i], Integer.parseInt(supplierPriceList[i]));
        }
        //获取排序类型
        Constant.SortType sortType = CommonalitySupport.sortTypeMap.get(supplierPageQueryDTO.getSortType());
        if (null == sortType) {
            throw new ApplicationException(AppResult.failed(ResultCode.SORT_TYPE_NOT_EXISTS));
        }
//        String sortKey = supplierPageQueryDTO.getSortKey();
        //将map中元素按照value的值进行排序，默认排序是升序
        supplierMap = supplierMap.entrySet()
                .stream()
                //判断排序类型执行对应排序
                .sorted(sortType.equal(Constant.SortType.ASC)
                        ? Map.Entry.comparingByValue()
                        : Map.Entry.comparingByValue(Comparator.reverseOrder())
                )
                .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (s1, s2) -> s1, LinkedHashMap::new
                        )
                );

        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<Supplier>()
                .in(Constant.SUPPLIER_ID, supplierIdList);
        Page<Supplier> supplierList = supplierMapper.selectPage(
                new Page<Supplier>(pag, pagesize),
                queryWrapper
        );
        //创建供应商信息列表，按照进价排序
        List<QuerySupplierListOfGoodsEntity> supplierListOfGoods = new ArrayList<>();
        List<Supplier> supplierListRecords = supplierList.getRecords();
        Iterator<Map.Entry<String, Integer>> iterator = supplierMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            Assert.notNull(entry, "supplierMap中存在null值");
            String key = entry.getKey();
            for (Supplier supplier : supplierListRecords) {
                if (key.equals(supplier.getSId())) {
                    supplierListOfGoods.add(new QuerySupplierListOfGoodsEntity(
                            supplier.getSId(),
                            supplier.getSName(),
                            supplier.getSPrincipal(),
                            supplier.getSPhone(),
                            supplier.getSAddress()
                    ));
                    break;
                }
            }
        }
        return new PageResult(
                supplierPageQueryDTO.getPage(),
                supplierPageQueryDTO.getPagesize(),
                supplierList.getTotal(),
                supplierListOfGoods
        );
    }
}




