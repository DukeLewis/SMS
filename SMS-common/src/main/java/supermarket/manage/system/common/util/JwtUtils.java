package supermarket.manage.system.common.util;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 *  * 获取JwtToken，获取JwtToken中封装的信息，判断JwtToken是否存在
 *  * 1. encode()，参数是=签发人，存在时间，一些其他的信息。返回值是JwtToken对应的字符串
 *  * 2. decode()，参数是=JwtToken=。返回值是荷载部分的键值对
 *  * 3. isVerify()，参数是=JwtToken=。返回值是这个JwtToken是否存在
 */
public class JwtUtils {

    // TOKEN的有效期1小时（S）
//    private static final int TOKEN_TIME_OUT = 7 * 24 * 3600;

    // 加密KEY
    private static final String TOKEN_SECRET = "9999999999";


    /**
     * 这里就是产生jwt字符串的地方
     * jwt字符串包括三个部分
     *  1. header
     *      -当前字符串的类型，一般都是“JWT”
     *      -哪种算法加密，“HS256”或者其他的加密算法
     *      所以一般都是固定的，没有什么变化
     *  2. payload
     *      一般有四个最常见的标准字段（下面有）
     *      iat：签发时间，也就是这个jwt什么时候生成的
     *      jti：JWT的唯一标识
     *      iss：签发人，一般都是username或者userId
     *      exp：过期时间
     * */
    public static String encode(String issuer,long ttlMillis,Map claims) {
        // iss签发人，ttlMillis生存时间，claims是指还想要在jwt中存储的一些非隐私信息
        long currentTime = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                // 这个是JWT的唯一标识，一般设置成唯一的，这个方法可以生成唯一标识
                .setId(UUID.randomUUID().toString())
                // 荷载部分
                .addClaims(claims)
                // 签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
                .setSubject(issuer)
                //这个地方是生成jwt使用的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET);
        if (ttlMillis >= 0) {
            long expMillis = currentTime + ttlMillis;
            Date exp = new Date(expMillis);//4. 过期时间，这个也是使用毫秒生成的，使用当前时间+前面传入的持续时间生成
            builder.setExpiration(exp);
        }
        return builder.compact();
    }


    // 相当于encode的方向，传入jwtToken生成对应的username和password等字段。Claim就是一个map
    // 也就是拿到荷载部分所有的键值对
    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token).getBody();
    }


    /**
     * 是否有效 true-有效，false-失效
     */
    @Deprecated
    public static boolean verifyToken(String token) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
