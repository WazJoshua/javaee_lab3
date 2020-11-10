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

    int deleteCustomer(int id);

    int updateCustomer(Customer customer);

    boolean updateCusById(Customer customer);
}
