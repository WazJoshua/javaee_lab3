package cn.edu.ujn.lab3.service;

import cn.edu.ujn.lab3.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
public interface ICustomerService {
    List<Customer> findAllCustomer();

}
