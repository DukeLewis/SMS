package supermarket.manage.system.service.inventory.event;

import org.springframework.context.ApplicationEvent;
import supermarket.manage.system.model.entity.InventoryEventEntity;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/16
 * @Copyright： https://github.com/DukeLewis
 */
public class InventoryUpdateEvent extends ApplicationEvent {
    public InventoryUpdateEvent(InventoryEventEntity inventoryEventEntity) {
        super(inventoryEventEntity);
    }
}
