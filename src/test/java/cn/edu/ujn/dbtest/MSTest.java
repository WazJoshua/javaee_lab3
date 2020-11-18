package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.dao.UserMapper;
import cn.edu.ujn.lab3.model.User;
import cn.edu.ujn.lab3.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:Joshua
 * @Date:2020/11/6
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MSTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    IUserService userService;

    @Test
    public void test01() {
        User user = new User();
        user.setPassword("123");
        user.setUsercode("m0001");
        boolean b = userService.loginUser(user);
        System.out.println("b = " + b);
    }

    @Test
    public void mapperTest() {
        User byUsercode = userMapper.findByUsercode("m0001");
        System.out.println("byUsercode = " + byUsercode);
    }

    @Test
    public void registTest(){
        User user = new User();
        user.setPassword("123");
        user.setUsercode("111");
        user.setUsername("123");
        user.setUserState(1);
        boolean b = userService.registerUser(user);
        System.out.println("b = " + b);
    }

    @Test
    public void loginTest(){
        User user = new User();
        user.setPassword("123");
        user.setUsercode("m0001");
        user.setUsername("123");
        user.setUserState(1);
        boolean b = userService.loginUser(user);
        System.out.println("b = " + b);
    }


}
