package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    List<Customer> selectCusBySel(@Param("customer") Customer customer);

    List<Customer> finaAllCustomer();

    int deleteByPrimaryKey(Integer custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer custId);

    List<Customer> selectSelective(@Param("record") Customer record);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}