package cn.edu.ujn.service;

import cn.edu.ujn.utils.CStringUtils;
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
    private int expireTime;

    protected static final long MILLS_SECOND = 1000;

    protected static final long MILLS_MIN = 60 * MILLS_SECOND;

    public static final String TOKEN_PREFIX = "Joshua ";


    /**
     * 获取token信息
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
