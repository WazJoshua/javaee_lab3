package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.dao.CustomerMapper;
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

    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void test01(){
        List<Customer> allCustomer = customerService.findAllCustomer();
        for (Customer c :
                allCustomer) {
            System.out.println("c = " + c);
        }
    }

    @Test
    public void test02(){
        Customer customer = new Customer();
        customer.setCustLevel("23");
        customer.setCustSource("7");
        List<Customer> customers = customerMapper.selectCusBySel(customer);
        for (Customer c :
                customers) {
            System.out.println("c = " + c);
        }
    }

    @Test
    public void test03(){
        Customer customer = new Customer();
        customer.setCustLevel("");
        customer.setCustSource("");
        customer.setCustIndustry("");
        customer.setCustName("");
        List<Customer> customers = customerService.selectCusBySelOV(customer);
        for (Customer c :
                customers) {
            System.out.println("c = " + c);
        }

    }

}
