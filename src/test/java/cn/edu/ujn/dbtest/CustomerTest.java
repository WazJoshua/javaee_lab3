package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.service.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerTest {
    @Autowired
    ICustomerService customerService;

    @Test
    public void test01(){
        List<Customer> allCustomer = customerService.findAllCustomer();
        for (Customer c :
                allCustomer) {
            System.out.println("c = " + c);
        }
    }

}
