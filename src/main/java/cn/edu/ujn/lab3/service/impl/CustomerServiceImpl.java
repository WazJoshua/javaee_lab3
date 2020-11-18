package cn.edu.ujn.lab3.service.impl;

import cn.edu.ujn.lab3.dao.BaseDictMapper;
import cn.edu.ujn.lab3.dao.CustomerMapper;
import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
@Transactional
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
    public List<Customer> selectCusBySelVO(Customer customer) {
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

    @Override
    public boolean insertCustomer(Customer customer) {
        java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        customer.setCustCreatetime(timeStamp);
        int insert = customerMapper.insert(customer);
        if (insert == -1) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> selectByPinyin(String pinyin) {
        return customerMapper.selectByPinyin(pinyin);
    }
}
