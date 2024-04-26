package supermarket.manage.system.event.listener;

import com.alibaba.fastjson2.JSON;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import supermarket.manage.system.controller.WarnController;
import supermarket.manage.system.model.entity.InventoryEventEntity;
import supermarket.manage.system.service.inventory.event.InventoryUpdateEvent;

import javax.annotation.Resource;

/**
 * @description: 库存更新事件监听器
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
@Component
public class InventoryUpdateEventListener {

    @Resource
    WarnController warnController;


    @EventListener(InventoryUpdateEvent.class)
    @Async("inventoryThreadPool")
    public void onApplicationEvent(InventoryUpdateEvent event) {
        InventoryEventEntity inventoryEventEntity = (InventoryEventEntity)event.getSource();
        warnController.sendAllMessage(JSON.toJSONString(inventoryEventEntity));
    }
}
