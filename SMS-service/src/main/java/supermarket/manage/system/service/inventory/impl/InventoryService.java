package supermarket.manage.system.service.inventory.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.DeletedType;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.domain.Inventory;
import supermarket.manage.system.model.dto.InventoryInfoDTO;
import supermarket.manage.system.model.dto.PageQueryDTO;
import supermarket.manage.system.model.entity.InventoryEventEntity;
import supermarket.manage.system.model.vo.PageResult;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.InventoryMapper;
import supermarket.manage.system.service.inventory.IInventoryService;
import supermarket.manage.system.service.inventory.event.InventoryUpdateEvent;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ASUS
 * @description 针对表【inventory】的数据库操作Service实现
 * @createDate 2024-04-15 19:52:14
 */
@Service
@Slf4j
public class InventoryService extends ServiceImpl<InventoryMapper, Inventory>
        implements IInventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addInventory(InventoryInfoDTO inventoryInfoDTO) {
        int inventoryNum=inventoryInfoDTO.getInboundNum()-inventoryInfoDTO.getOutboundNum();
        boolean flg = save(Inventory.builder()
                .gId(inventoryInfoDTO.getGid())
                .gName(inventoryInfoDTO.getGname())
                .gCategory(inventoryInfoDTO.getGcategory())
                .inboundNum(inventoryInfoDTO.getInboundNum())
                .inboundTime(inventoryInfoDTO.getInboundTime())
                .supplier(inventoryInfoDTO.getSupplier())
                .outboundNum(inventoryInfoDTO.getOutboundNum())
                .outboundTime(inventoryInfoDTO.getOutboundTime())
                .purpose(inventoryInfoDTO.getPurpose())
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(0).build())
                &&
                goodsMapper.update(null,
                        new UpdateWrapper<Goods>()
                                .eq(Constant.GOODS_ID, inventoryInfoDTO.getGid())
                                .eq(Constant.IS_DELETED, DeletedType.UN_DELETED)
                                .setSql("inventory = inventory + " + inventoryNum)
                ) > 0;
        //todo 如果前端无法实现，考虑将取消该事件
        try {
            applicationContext.publishEvent(new InventoryUpdateEvent(new InventoryEventEntity(
                            inventoryInfoDTO.getGid(),
                            inventoryInfoDTO.getGname(),
                            inventoryNum>0?InventoryEventEntity.Type.IN.getType() : InventoryEventEntity.Type.OUT.getType(),
                            Math.abs(inventoryNum))
                    )
            );
        } catch (Exception e) {
            log.info("入库消息推送失败");
        }
        return flg;
    }

    @Override
    public boolean delInventory(Integer id) {
        return update(null,
                new UpdateWrapper<Inventory>()
                        .eq("id",id)
                        .set(Constant.IS_DELETED, 1)
        );
    }

    @Override
    public PageResult queryInventory(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Inventory> page = inventoryMapper.selectPage(
                new Page<Inventory>(pag, pagesize),
                new QueryWrapper<Inventory>().eq(Constant.GOODS_NAME, pageQueryDTO.getKeyword())
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }

    @Override
    public PageResult queryInventoryAll(PageQueryDTO pageQueryDTO) {
        Integer pag = pageQueryDTO.getPage();
        Integer pagesize = pageQueryDTO.getPagesize();

        Page<Inventory> page = inventoryMapper.selectPage(
                new Page<Inventory>(pag, pagesize),
                new QueryWrapper<Inventory>()
                        //0为未删除，1为已删除
                        .ne(Constant.IS_DELETED, DeletedType.DELETED)
        );
        return new PageResult(
                pag, pagesize, page.getTotal(), page.getRecords()
        );
    }
}




