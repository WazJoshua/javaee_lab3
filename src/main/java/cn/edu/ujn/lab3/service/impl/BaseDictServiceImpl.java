package cn.edu.ujn.lab3.service.impl;

import cn.edu.ujn.lab3.dao.BaseDictMapper;
import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.service.IBaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
@Service
public class BaseDictServiceImpl implements IBaseDictService {

    @Autowired
    BaseDictMapper baseDictMapper;

    @Override
    public List<BaseDict> findDicByDicType(String dicCode) {
        List<BaseDict> baseDicts = baseDictMapper.selectByTypeId(dicCode);
      /*  System.out.println("baseDicts = " + baseDicts);
        List<String> dictItemNames =new ArrayList<>();
        for (BaseDict b :
                baseDicts) {
            dictItemNames.add(b.getDictItemName());
        }*/
        return baseDicts;
    }

    @Override
    public boolean insertDictSource(BaseDict baseDict) {
        int insert = baseDictMapper.insert(baseDict);
        if (insert == -1) {
            return false;
        }
        return true;
    }

    @Override
    public int selectIdMaximum() {
        return baseDictMapper.selectIdMaximum();
    }

    @Override
    public int selectSortMaximum() {
        return baseDictMapper.selectSortMaximum();
    }


}
