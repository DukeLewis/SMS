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

    public Map<String, SortType> sortTypeMap=new HashMap<>();

    @PostConstruct
    public void init(){
        sortTypeMap.put(Constant.SortType.ASC.getInfo(), new SortAsc());
        sortTypeMap.put(Constant.SortType.DESC.getInfo(), new SortDesc());
    }

    public interface SortType{
        /**
         * 获取排序的QueryWrapper
         * @param qw 待处理的QueryWrapper
         * @param key 排序的关键字
         * @return 处理后的QueryWrapper
         */
        QueryWrapper sortBy(QueryWrapper qw,String key);
    }

    public static class SortAsc implements SortType{
        public QueryWrapper sortBy(QueryWrapper qw,String key){
            return (QueryWrapper) qw.orderByAsc(key);
        }
    }

    public static class SortDesc implements SortType{
        public QueryWrapper sortBy(QueryWrapper qw,String key){
            return (QueryWrapper) qw.orderByDesc(key);
        }
    }

}
