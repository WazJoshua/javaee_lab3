package cn.edu.ujn.dbtest;

import com.github.pagehelper.PageHelper;
import org.junit.Test;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */

public class PageHelperTest {

    @Test
    public void test01(){
        PageHelper.startPage(1,5);
    }

}
