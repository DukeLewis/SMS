package supermarket.manage.system.common.util;

import supermarket.manage.system.common.commons.Constant;

import java.util.List;

/**
 * @description: List工具类，将List类转换成其他类型
 * @author：dukelewis
 * @date: 2024/5/9
 * @Copyright： https://github.com/DukeLewis
 */
public class ListUtil {

    /**
     * 将List<String>转换成String
     * @param list
     * @return
     */
    public static String list2String(List<String> list){
        if(null==list||list.isEmpty()){
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (String s : list) {
            res.append(s).append(Constant.DATABASE_SPLIT);
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }
}
