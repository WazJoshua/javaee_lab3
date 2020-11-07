package cn.edu.ujn.lab3.service.impl;

import cn.edu.ujn.lab3.dao.CustomerMapper;
import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerMapper customerMapper;


    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customers = customerMapper.finaAllCustomer();
        return customers;
    }
}
