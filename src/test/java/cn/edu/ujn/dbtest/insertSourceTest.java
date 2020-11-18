package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.dao.BaseDictMapper;
import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.service.IBaseDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @Author:bbimix
 * @Date:2020/11/10
 */

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class insertSourceTest {

    @Autowired
    IBaseDictService baseDictService;

    @Autowired
    BaseDictMapper baseDictMapper;

/*    @Test
    public void Test01(){
        BaseDict baseDict = new BaseDict();
        baseDict.setDictEnable("1");
        baseDict.setDictTypeCode("001");
        baseDict.setDictItemName("dancer");
        baseDict.setDictTypeName("客户行业");
//        baseDict.setDictSort(baseDictService.selectSortMaximum()+1);
        baseDict.setDictId(Integer.toString(baseDictService.selectIdMaximum()+1));
        baseDictService.insertDictSource(baseDict);
    }*/


}
