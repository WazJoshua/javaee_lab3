package cn.edu.ujn.lab3.service.impl;

import cn.edu.ujn.lab3.dao.BaseDictMapper;
import cn.edu.ujn.lab3.dao.CustomerMapper;
import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    BaseDictMapper baseDictMapper;


    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customers = customerMapper.finaAllCustomer();
        return customers;
    }



    @Override
    public List<Customer> selectCusBySelOV(Customer customer) {
        List<Customer> customers = customerMapper.selectCusBySel(customer);
        List<Customer> nCustomers = new ArrayList<Customer>();
        for (Customer c :
                customers) {
            BaseDict cusSource = baseDictMapper.selectByPrimaryKey(c.getCustSource());
            BaseDict cusIndustry = baseDictMapper.selectByPrimaryKey(c.getCustIndustry());
            BaseDict cusLevel = baseDictMapper.selectByPrimaryKey(c.getCustLevel());
            c.setCustSource(cusSource.getDictItemName());
            c.setCustIndustry(cusIndustry.getDictItemName());
            c.setCustLevel(cusLevel.getDictItemName());
            nCustomers.add(c);
        }
        return nCustomers;
    }
    @Override
    public List<Customer> selectCusBySel(Customer customer) {
        List<Customer> customers = customerMapper.selectCusBySel(customer);
        return customers;
    }
}
