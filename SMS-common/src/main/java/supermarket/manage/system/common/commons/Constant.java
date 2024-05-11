package supermarket.manage.system.common.commons;

import java.sql.Date;

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


    public static final String Finance_DATE = "record_time";


    public static final String Supplier_Name = "s_name";

    public static final String Supplier_Id = "s_id";

    public static class ResultCode{

        //关于员工模块的错误描述
        public static final int FAILED_EMPLOYEE_NOT_EXISTS_CODE= 1501;

        public static final String FAILED_EMPLOYEE_NOT_EXISTS_MSG= "员工不存在";

        public static final int FAILED_EMPLOYEE_BANNED_CODE = 1502;
        public static final String FAILED_EMPLOYEE_BANNED_MSG = "员工状况异常";

        public static final int FAILED_EMPLOYEE_EXISTS_CODE = 1503;
        public static final String FAILED_EMPLOYEE_EXISTS_MSG = "员工已存在";

        public static final int FAILED_EMPLOYEE_ENTER_CODE = 1504;
        public static final String FAILED_EMPLOYEE_ENTER_MSG = "员工信息录入失败";

        public static final int FAILED_EMPLOYEE_MODIFY_CODE = 1505;
        public static final String FAILED_EMPLOYEE_MODIFY_MSG = "员工信息修改失败";

        public static final int FAILED_EMPLOYEE_DELETE_CODE = 1506;
        public static final String FAILED_EMPLOYEE_DELETE_MSG = "员工信息删除失败";

        public static final int FAILED_EMPLOYEE_QUERY_CODE = 1507;
        public static final String FAILED_EMPLOYEE_QUERY_MSG = "员工信息查询失败";

        // 关于用户的错误描述
        public static final int FAILED_USER_EXISTS_CODE = 1101;
        public static final String FAILED_USER_EXISTS_MSG = "用户已存在";

        public static final int FAILED_USER_NOT_EXISTS_CODE = 1102;
        public static final String FAILED_USER_NOT_EXISTS_MSG = "用户不存在";

        public static final int FAILED_LOGIN_CODE = 1103;
        public static final String FAILED_LOGIN_MSG = "用户名或密码错误";

        public static final int FAILED_USER_BANNED_CODE = 1104;
        public static final String FAILED_USER_BANNED_MSG = "您已被禁言, 请联系管理员, 并重新登录.";

        public static final int FAILED_USER_ARTICLE_COUNT_CODE = 1105;
        public static final String FAILED_USER_ARTICLE_COUNT_MSG = "更新帖子数量失败";

        public static final int FAILED_TWO_PWD_NOT_SAME_CODE = 1106;
        public static final String FAILED_TWO_PWD_NOT_SAME_MSG = "两次输入的密码不一致";

        public static final int FAILED_USER_PWD_NULL_CODE = 1107;
        public static final String FAILED_USER_PWD_NULL_MSG = "用户名或者密码为空";

        public static final int FAILED_USER_LOGIN_ERROR_CODE = 1108;
        public static final String FAILED_USER_LOGIN_ERROR_MSG = "登录错误";

        public static final int FAILED_USER_REGISTER_ERROR_CODE = 1109;
        public static final String FAILED_USER_REGISTER_ERROR_MSG = "注册错误";

    }



}
