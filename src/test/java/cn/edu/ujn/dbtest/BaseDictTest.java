package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.model.CustomerWithPageNumber;
import cn.edu.ujn.lab3.service.IBaseDictService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BaseDictTest {

    @Autowired
    IBaseDictService baseDictService;

    @Test
    public void test01() {
        /*List<String> cusSources = baseDictService.findDicByDicType("003");
        System.out.println("cusSources = " + cusSources);
        for (String s :
                cusSources) {
            System.out.println("s = " + s);
        }*/
    }

    @Test
    public void test02() {
        String j = "{\"pn\":\"1\",\"customer\":{\"custName\":\"\",\"custSource\":\"\",\"custIndustry\":\"\",\"custLevel\":\"\"}}";

        Gson gson = new Gson();
        CustomerWithPageNumber customer = gson.fromJson(j, new TypeToken<CustomerWithPageNumber>(){}.getType());
        System.out.println("customer = " + customer);
    }

    @Test
    public void test03(){
        List<BaseDict> baseDicts = baseDictService.findAllTypeName();
        for(BaseDict b : baseDicts){
            System.out.println(b.getDictTypeName());
        }
    }
}
