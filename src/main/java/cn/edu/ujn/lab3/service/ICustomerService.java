package cn.edu.ujn.lab3.service;

import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.service.impl.CUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
public interface ICustomerService {
    List<Customer> findAllCustomer();

    List<Customer> selectCusBySelVO(Customer customer);

    List<Customer> selectCusBySel(Customer customer);

    boolean insertCustomer(Customer customer);

    public boolean updateCusById(Customer customer);

    boolean deleteCusById(Integer id);

    List<String> selectByPinyin(String pinyin);

}
