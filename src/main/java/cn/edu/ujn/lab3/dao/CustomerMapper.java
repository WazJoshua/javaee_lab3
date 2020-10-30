package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.model.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer custId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}