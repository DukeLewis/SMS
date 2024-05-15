package supermarket.manage.system.service.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.common.commons.Constant;
import supermarket.manage.system.common.commons.enumeration.ModuleType;
import supermarket.manage.system.common.commons.enumeration.ResultCode;
import supermarket.manage.system.common.exception.ApplicationException;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 商品服务的基础数据支持类
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
@Component
@Slf4j
public class CommonalitySupport {

    public static final Map<String, Constant.SortType> sortTypeMap=new HashMap<>();

    public static final Map<String, Constant.KeyWordType> keyWordTypeMap=new HashMap<>();

    @PostConstruct
    public void init(){
        sortTypeMap.put(Constant.SortType.ASC.getInfo(), Constant.SortType.ASC);
        sortTypeMap.put(Constant.SortType.DESC.getInfo(), Constant.SortType.DESC);

        keyWordTypeMap.put(Constant.KeyWordType.CATEGORY.getInfo(), Constant.KeyWordType.CATEGORY);
        keyWordTypeMap.put(Constant.KeyWordType.NAME.getInfo(), Constant.KeyWordType.NAME);
        keyWordTypeMap.put(Constant.KeyWordType.ID.getInfo(), Constant.KeyWordType.ID);
        keyWordTypeMap.put(Constant.KeyWordType.TIME.getInfo(), Constant.KeyWordType.TIME);
        keyWordTypeMap.put(Constant.KeyWordType.SALESMAN.getInfo(), Constant.KeyWordType.SALESMAN);
        keyWordTypeMap.put(Constant.KeyWordType.BRAND.getInfo(), Constant.KeyWordType.BRAND);
        keyWordTypeMap.put(Constant.KeyWordType.POSITION.getInfo(), Constant.KeyWordType.POSITION);

    }

    public static String getQueryType(String keywordType, ModuleType moduleType){
        Constant.KeyWordType wordType = keyWordTypeMap.get(keywordType);

        if (null == wordType) {
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        }
        Class<Constant> constantClass = Constant.class;
        String fieldName = wordType.getInfo().toUpperCase();

        fieldName=moduleType.getInfo().toUpperCase()+Constant.QUERY_TYPE+fieldName;

        try {
            Field field = constantClass.getField(fieldName);


            String res=(String)field.get(null);
            return res;
        } catch (NoSuchFieldException e) {
            log.error("查询字段名称不存在或者未定义");
            throw new ApplicationException(AppResult.failed(ResultCode.KEYWORD_TYPE_NOT_EXISTS));
        } catch (IllegalAccessException e) {
            log.error("反射访问权限非法错误");
            throw new RuntimeException(e.getCause());
        }
    }

}
