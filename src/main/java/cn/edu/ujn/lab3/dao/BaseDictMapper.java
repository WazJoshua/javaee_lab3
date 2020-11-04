package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.model.BaseDict;

public interface BaseDictMapper {
    int deleteByPrimaryKey(String dictId);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    BaseDict selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);
}