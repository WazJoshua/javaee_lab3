package cn.edu.ujn.lab3.dao;

import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDictMapper {
    List<BaseDict> selectByTypeId(String id);

    int deleteByPrimaryKey(String dictId);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    BaseDict selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);

    int selectIdMaximum();

    int selectSortMaximum(String s);

    int selectTypeCodeMaximum();

    List<BaseDict> selectDictBySel(BaseDict baseDict);

    List<BaseDict> selectAllTypeName();
}