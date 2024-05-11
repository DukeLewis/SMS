package supermarket.manage.system.service.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import supermarket.manage.system.common.commons.Constant;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 商品服务的基础数据支持类
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
@Component
public class GoodsSupport {

    public static final Map<String, Constant.SortType> sortTypeMap=new HashMap<>();

    public static final Map<String, Constant.KeyWordType> keyWordTypeMap=new HashMap<>();

    @PostConstruct
    public void init(){
        sortTypeMap.put(Constant.SortType.ASC.getInfo(), Constant.SortType.ASC);
        sortTypeMap.put(Constant.SortType.DESC.getInfo(), Constant.SortType.DESC);

        keyWordTypeMap.put(Constant.KeyWordType.CATEGORY.getInfo(), Constant.KeyWordType.CATEGORY);
        keyWordTypeMap.put(Constant.KeyWordType.NAME.getInfo(), Constant.KeyWordType.NAME);
    }

}
