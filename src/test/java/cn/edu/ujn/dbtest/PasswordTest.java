package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.model.User;
import cn.edu.ujn.lab3.utils.BCrypt;
import org.junit.Test;

/**
 * @Author:Joshua
 * @Date:2020/11/17
 */
public class PasswordTest {

    @Test
    public void test01(){
        User u=new User();
        u.setPassword("qwe");
        User u2=new User();
        u2.setPassword("123");
        System.out.println("u = " + u);
        System.out.println(BCrypt.checkpw("123",u.getPassword()));
    }
}
