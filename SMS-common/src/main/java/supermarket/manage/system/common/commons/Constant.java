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
     * 员工姓名（条件查询时使用）
     */
    public static final String EMPLOYEE_QUERY_NAME = "e_name";

    /**
     * 员工id
     */
    public static final String EMPLOYEE_ID = "e_id";

    /**
     * 员工id（条件查询时使用）
     */
    public static final String EMPLOYEE_QUERY_ID = "e_id";

    /**
     * 员工岗位（员工分类）（条件查询时使用）
     */
    public static final String EMPLOYEE_QUERY_CATEGORY = "position";

    /**
     * 商品名称
     */
    public static final String GOODS_NAME = "g_name";

    /**
     * 商品名称（条件查询时使用）
     */
    public static final String GOODS_QUERY_NAME = "g_name";

    /**
     * 商品类型
     */
    public static final String GOODS_CATEGORY = "g_category";

    /**
     * 商品类型（条件查询时使用）
     */
    public static final String GOODS_QUERY_CATEGORY = "g_category";

    /**
     * 商品id
     */
    public static final String GOODS_ID="g_id";

    /**
     * 商品id（条件查询时使用）
     */
    public static final String GOODS_QUERY_ID="g_id";

    /**
     * 是否删除
     */
    public static final String IS_DELETED = "is_deleted";

    /**
     * 进货id
     */
    public static final String RESTOCK_ID = "r_id";

    /**
     * 进货id（条件查询时使用）
     */
    public static final String RESTOCK_QUERY_ID = "r_id";

    /**
     * 数据库对于一个字符串的截取符号
     */
    public static final String DATABASE_SPLIT = ",";

    /**
     * 供应商id
     */
    public static final String SUPPLIER_ID = "s_id";

    /**
     * 供应商id（条件查询时使用）
     */
    public static final String SUPPLIER_QUERY_ID = "s_id";

    /**
     * 供应商名称（条件查询时使用）
     */
    public static final String SUPPLY_QUERY_NAME = "s_name";

    /**
     * 销售员（条件查询时使用）
     */
    public static final String SALES_QUERY_SALESMAN = "saler";

    /**
     * 销售id（条件查询时使用）
     */
    public static final String SALES_QUERY_ID = "g_id";

    /**
     * 销售名称（条件查询时使用）
     */
    public static final String SALES_QUERT_NAME = "g_name";

    /**
     * 销售时间（条件查询时使用）
     */
    public static final String SALES_QUERY_TIME = "sale_time";


    /**
     * 库存id（条件查询时使用）
     */
    public static final String INVENTORY_QUERY_ID = "g_id";

    /**
     * 库存名称（条件查询时使用）
     */
    public static final String INVENTORY_QUERY_CATEGORY = "g_name";

    /**
     *  创建时间
     */
    public static final String CREATE_DATE="create_time";

    /**
     * 关于条件查询的对应条件类型获得的拼接字符串
     */
    public static final String QUERY_TYPE="_QUERY_";


    @Getter
    @AllArgsConstructor
    public enum SortType{
        ASC("asc"),
        DESC("desc");

        /**
         * 描述
         */
        private final String info;

        public boolean equal(SortType sortType){
            return this.info.equals(sortType.getInfo());
        }
    }

    @Getter
    @AllArgsConstructor
    public enum KeyWordType{
        CATEGORY("category"),
        NAME("name"),
        ID("id"),
        TIME("time"),
        SALESMAN("salesman");

        /**
         * 描述
         */
        private final String info;

        public boolean equal(KeyWordType keyWordType){
            return this.info.equals(keyWordType.getInfo());
        }
    }



}
