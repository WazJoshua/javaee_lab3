package cn.edu.ujn.lab3.service;

import cn.edu.ujn.lab3.model.BaseDict;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
public interface IBaseDictService {

    List<BaseDict> findDicByDicType(String dicCode);

}
