package supermarket.manage.system.common.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 公共常量
 * @author：dukelewis
 * @date: 2024/4/8
 * @Copyright： https://github.com/DukeLewis
 */
public class Constant {

    /**
     * 账号
     */
    public static final String USERNAME = "u_name";

    /**
     * 密码
     */
    public static final String PASSWORD = "password";

    /**
     * 员工姓名
     */
    public static final String EMPLOYEE_NAME = "e_name";

    /**
     * 商品名称
     */
    public static final String GOODS_NAME = "g_name";

    /**
     * 商品id
     */
    public static final String GOODS_ID="g_id";

    /**
     * 是否删除
     */
    public static final String IS_DELETED = "is_deleted";

    /**
     * 进货id
     */
    public static final String RESTOCK_ID = "r_id";

    /**
     * 数据库对于一个字符串的截取符号
     */
    public static final String DATABASE_SPLIT = ",";

    /**
     * 供应商id
     */
    public static final String SUPPLIER_ID = "s_id";


    @Getter
    @AllArgsConstructor
    public enum SortType{
        ASC("asc"),
        DESC("desc");

        /**
         * 描述
         */
        private final String info;
    }



}
