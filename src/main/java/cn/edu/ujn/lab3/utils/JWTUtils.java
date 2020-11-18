package cn.edu.ujn.lab3.utils;

import cn.edu.ujn.lab3.model.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {
    //过期时间 一天,单位 ms
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //密钥
    public static final String APP_SECRET = "justwriteitsimply";

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String genJWT(User user) {
        if (user == null || user.getUsercode() == null || user.getUsername() == null || user.getPassword() == null) {
            return null;
        }

        String token = Jwts.builder().setSubject("sys_user")
                .claim("id", user.getUsercode())
                .claim("name", user.getUsername())
                .setIssuedAt(new Date())                        //签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))   //过期时间
                .signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();      //加密
        return token;
    }


    public static Claims parseJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkJWT(String token) {
        Claims claims = parseJWT(token);
        if (claims == null)
            return false;
        return true;
    }
}
