package supermarket.manage.system.service.restock.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.RestockStatus;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;
import supermarket.manage.system.common.util.ListUtil;
import supermarket.manage.system.model.domain.*;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.dto.RestockInfoDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.InventoryMapper;
import supermarket.manage.system.repository.mysql.mapper.RestockMapper;
import supermarket.manage.system.service.finance.FinanceService;
import supermarket.manage.system.service.goods.IGoodsService;
import supermarket.manage.system.service.inventory.IInventoryService;
import supermarket.manage.system.service.restock.IRestockService;
import supermarket.manage.system.service.sales.SalesService;
import supermarket.manage.system.service.support.CommonalitySupport;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private IInventoryService inventoryService;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private FinanceService financeService;

    @Resource
    private InventoryMapper inventoryMapper;
    @Getter
    @Setter
    public static class GoodsAndInventoryModel {
        private String number;
        private String gName;
        private String gCategory;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRestock(RestockInfoDTO restockInfoDTO) {
        List<String> productNumberList = restockInfoDTO.getProductNumberList();
        List<String> productIdList = restockInfoDTO.getProductIdList();
        List<String> supplierList = restockInfoDTO.getSupplierList();
        List<String> productPricelist = restockInfoDTO.getProductPricelist();
        Map<String, GoodsAndInventoryModel> modelMap = new HashMap<>();
        double sum = 0;
        for (int i = 0; i < productIdList.size()&&productIdList.get(i)!=null&&productIdList.get(i)!=""; i++) {
            GoodsAndInventoryModel model = new GoodsAndInventoryModel();
            model.setNumber(productNumberList.get(i));
            sum += Double.parseDouble(productNumberList.get(i)) * Double.parseDouble(productPricelist.get(i));
            modelMap.put(productIdList.get(i), model);
        }
        List<Goods> goodsList = goodsService.lambdaQuery().in(Goods::getGId, productIdList).list();
        goodsList = goodsList.stream().map(goods -> {
            GoodsAndInventoryModel model = modelMap.get(goods.getGId().toString());
            goods.setInventory(goods.getInventory() + Integer.parseInt(model.getNumber()));
            model.setGName(goods.getGName());
            model.setGCategory(goods.getGCategory());
            modelMap.put(goods.getGId().toString(), model);
            return goods;
        }).collect(Collectors.toList());
        boolean goodUpdateBatch = goodsService.updateBatchById(goodsList);
        List<Inventory> inventoryList = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < productIdList.size(); i++) {
            GoodsAndInventoryModel model = modelMap.get(productIdList.get(i));
            if (null != model) {
                int gid = Integer.parseInt(productIdList.get(i));
                int number = Integer.parseInt(productNumberList.get(i));
                Inventory inventory = inventoryMapper.selectByGID(gid);
                Integer Iid = inventory.getId();
                Integer res = inventoryMapper.updateById(Inventory.builder()
                        .id(Iid)
                        .inboundNum(number+inventory.getInboundNum())
                        .inboundTime(restockInfoDTO.getArriveTime())
                        .outboundTime(inventory.getOutboundTime())
                        .supplier(inventory.getSupplier()+"、"+supplierList.get(i))
                        .purpose(inventory.getPurpose()+"、进货")
                        .gCategory(inventory.getGCategory())
                        .build());
                //                inventoryList.add(Inventory.builder()
//                                .gId(gid)
//                                .inboundNum(number)
//                                .inboundTime(restockInfoDTO.getArriveTime())
//                                .supplier(supplierList.get(i))
//                                .purpose("进货")
//                                .gName(model.getGName())
//                                .gCategory(model.getGCategory())
//                                .createTime(date)
//                                .updateTime(date)
//                                .isDeleted(0)
//                                .build()
//                );

            }
        }
        boolean fin = financeService.save(Finance.builder()
                .recordTime(date)
                .fType(1)
                .amount(sum)
                .remark("进货")
                .updateTime(date)
                .createTime(date)
                .isDeleted(0)
                .build()
        );
//        boolean inventoryBatch = inventoryService.saveBatch(inventoryList);
        return save(Restock.builder()
                .productIdList(ListUtil.list2String(restockInfoDTO.getProductIdList()))
                .supplierList(ListUtil.list2String(restockInfoDTO.getSupplierList()))
                .productPricelist(ListUtil.list2String(restockInfoDTO.getProductPricelist()))
                .productNumberList(ListUtil.list2String(restockInfoDTO.getProductNumberList()))
                .arriveTime(restockInfoDTO.getArriveTime())
                .status(RestockStatus.UN_ARRIVED.getStatus())
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(0).build())
                && goodUpdateBatch  && fin;
//                && goodUpdateBatch && inventoryBatch && fin;
    }

    @Override
    public boolean updateRestock(RestockInfoDTO restockInfoDTO) {
        Restock restock = getById(restockInfoDTO.getRId());
        if (null == restock || DeletedType.DELETED.getCode().equals(restock.getIsDeleted())) {
            throw new ApplicationException(AppResult.failed(ResultCode.RESTOCK_NOT_EXISTS));
        }
        return updateById(
                Restock.builder()
                        .rId(restockInfoDTO.getRId())
                        .productIdList(ListUtil.list2String(restockInfoDTO.getProductIdList()))
                        .supplierList(ListUtil.list2String(restockInfoDTO.getSupplierList()))
                        .productPricelist(ListUtil.list2String(restockInfoDTO.getProductPricelist()))
                        .productNumberList(ListUtil.list2String(restockInfoDTO.getProductNumberList()))
                        .arriveTime(restockInfoDTO.getArriveTime())
                        .status(restockInfoDTO.getStatus())
                        //注意，如果这里进行了更改那么就是删除该记录
                        .isDeleted(restockInfoDTO.getIsDeleted())
                        .updateTime(new Date())
                        .build()
        );
    }

    @Override
    public PageResult queryRestockALL(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Restock> page = restockMapper.selectPage(
                new Page<Restock>(pag, pagesize),
                new QueryWrapper<Restock>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED.getCode())
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }


    @Override
    public PageResult queryRestock(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        if (null == pageQueryDTO.getKeyword() || null == pageQueryDTO.getKeywordType()) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_NOT_EXISTS));
        }
        //获取查询类型
        String queryTypeName = CommonalitySupport.getQueryType(pageQueryDTO.getKeywordType(), ModuleType.RESTOCK);
        if (null == queryTypeName) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        }

        Page<Restock> page = restockMapper.selectPage(
                new Page<Restock>(pag, pagesize),
                new QueryWrapper<Restock>().eq(queryTypeName, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, 1)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

}




