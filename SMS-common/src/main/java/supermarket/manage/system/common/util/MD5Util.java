package supermarket.manage.system.common.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

//用于MD5加密的工具类
public class MD5Util {
    /**
     * 对字符串进行MD5加密
     * @param str 明文
     * @return 密文
     */
    public static String md5 (String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 对用户密码进行加密
     * @param str 密码明文
     * @param salt 扰动字符
     * @return 密文
     */
    public static String md5Salt (String str, String salt) {
        return md5(md5(str) + salt);
    }


}
