package supermarket.manage.system.service.goods.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import supermarket.manage.system.common.annotation.InfoLog;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.InfoType;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.dto.GoodsInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.service.goods.IGoodsService;

import javax.annotation.Resource;
import java.util.Date;

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

    //todo 录入商品信息同时需要新增入库信息
    @Override
    @InfoLog(infoType = InfoType.ADD,item = "商品",infoItemIdExpression = "#goodsInfoDTO.gid")
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
                .supplierList(goodsInfoDTO.getSupplierList())
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
                .supplierList(goodsInfoDTO.getSupplierList())
                .build());
    }

    @Override
    public PageResult informationQuery(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Goods> page = goodsMapper.selectPage(
                new Page<Goods>(pag,pagesize),
                new QueryWrapper<Goods>().eq(Constant.GOODS_NAME, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED,1)
        );
        return new PageResult(
                pag,pagesize,page.getTotal(),page.getRecords()
        );
    }
}




