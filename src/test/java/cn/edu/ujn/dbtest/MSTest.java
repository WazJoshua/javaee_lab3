package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.dao.UserMapper;
import cn.edu.ujn.lab3.model.User;
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

    @Test
    public void mapperTest(){
        User byUsercode = userMapper.findByUsercode("1111");
        System.out.println("byUsercode = " + byUsercode);
    }

}
