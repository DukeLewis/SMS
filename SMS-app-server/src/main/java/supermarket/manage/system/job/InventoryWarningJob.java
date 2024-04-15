package supermarket.manage.system.job;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import supermarket.manage.system.controller.WarnController;
import supermarket.manage.system.model.domain.Goods;
import supermarket.manage.system.model.entity.InventoryWarnEntity;
import supermarket.manage.system.repository.mysql.mapper.GoodsMapper;
import supermarket.manage.system.service.inventory.IInventoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 定时扫描库存，库存低于某个阈值时，发送预警信息
 * @author：dukelewis
 * @date: 2024/4/15
 * @Copyright： https://github.com/DukeLewis
 */
@Slf4j
@Component
public class InventoryWarningJob {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    WarnController warnController;


    //todo 考虑时间间隔多少合适
    @Scheduled(cron = "0 0/1 * * * ?")
    public void exec(){
        List<InventoryWarnEntity> warnEntityList = goodsMapper.findInventoryWarn();
        warnController.sendAllMessage(JSON.toJSONString(warnEntityList));
    }

}
