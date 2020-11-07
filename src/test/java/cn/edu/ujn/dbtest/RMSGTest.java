package cn.edu.ujn.dbtest;

import cn.edu.ujn.lab3.model.ResultMSG;
import org.junit.Test;

/**
 * @Author:Joshua
 * @Date:2020/11/6
 */
public class RMSGTest {

    @Test
    public void test01(){
        ResultMSG success = ResultMSG.success();
        success.put("token","token");
        System.out.println("success = " + success);
    }
}
