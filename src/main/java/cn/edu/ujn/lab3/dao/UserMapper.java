package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.model.User;

public interface UserMapper {
    User findByUserCode(String usercode);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}