package supermarket.manage.system.job;

import com.alibaba.fastjson2.JSON;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import supermarket.manage.system.controller.WarnController;
import supermarket.manage.system.model.entity.InventoryWarnEntity;
import supermarket.manage.system.model.entity.RestockWarnEntity;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.repository.mysql.mapper.RestockMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 定时任务，定时扫描超时未到货的进货单
 * @author：dukelewis
 * @date: 2024/4/21
 * @Copyright： https://github.com/DukeLewis
 */
@Component
public class RestockStatusJob {

    @Resource
    private RestockMapper restockMapper;

    @Resource
    WarnController warnController;


    //todo 考虑时间间隔多少合适
    @Scheduled(cron = "0 0/1 * * * ?")
    public void exec(){
        List<RestockWarnEntity> restockStatusWarn = restockMapper.findRestockStatusWarn();
        if(restockStatusWarn==null){
            return;
        }
        warnController.sendAllMessage(JSON.toJSONString(restockStatusWarn));
    }
}
