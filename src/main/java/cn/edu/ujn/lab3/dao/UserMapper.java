package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByUsercode(String usercode);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}