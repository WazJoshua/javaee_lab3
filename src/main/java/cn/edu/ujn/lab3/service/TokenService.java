package cn.edu.ujn.lab3.service;

import cn.edu.ujn.lab3.model.User;
import cn.edu.ujn.lab3.utils.CStringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:Joshua
 * @Date:2020/10/30
 */
@Component
public class TokenService {

    //令牌标识
    @Value("${token.header}")
    private String header;

    //令牌密钥
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private String expireTime;

    protected static final long MILLS_SECOND = 1000;

    protected static final long MILLS_MIN = 60 * MILLS_SECOND;

    public static final String TOKEN_PREFIX = "Joshua ";

    /**
     * 获取用户身份信息
     *
     * @param request
     * @return 用户信息
     */
    public User getLoginUser(HttpServletRequest request) {

        String token = getToken(request);
        if (CStringUtils.isNotEmpty(token)) {
            Claims claims = praseToken(token);
            String uuid = (String) claims.get("login_user_key");
            String tokenKey = getTokenKey(uuid);
        }
        return null;
    }

    private String getTokenKey(String uuid){
        return "login_user_key"+uuid;
    }

    private Claims praseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取token信息
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (CStringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }


}
