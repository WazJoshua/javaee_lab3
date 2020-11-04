package cn.edu.ujn.lab3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Joshua
 * @Date:2020/10/30
 */
@Component
public class ShiroService {

    /*@Autowired
    JdbcRealm jdbcRealm;

    @Autowired
    DefaultWebSecurityManager defaultWebSecurityManager;

    public Subject getSubject() {
        defaultWebSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }*/
}
