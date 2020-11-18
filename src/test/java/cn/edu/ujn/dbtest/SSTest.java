package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.dao.UserMapper;
import cn.edu.ujn.lab3.service.impl.CUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:Joshua
 * @Date:2020/11/11
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SSTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Test
    public void test01(){
        UserDetails m0001 = userDetailsService.loadUserByUsername("m01");
        System.out.println("m0001 = " + m0001);
    }
}
