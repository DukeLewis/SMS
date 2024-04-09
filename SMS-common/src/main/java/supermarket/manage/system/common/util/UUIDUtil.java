package supermarket.manage.system.common.util;

import java.util.UUID;

public class UUIDUtil {

    //生成一个UUID 36位
    public static String UUID_36(){
        return UUID.randomUUID().toString();

    }
    //生成一个UUID 32位
    public static String UUID_32(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
