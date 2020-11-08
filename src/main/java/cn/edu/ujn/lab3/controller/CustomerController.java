package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.model.ResultMSG;
import cn.edu.ujn.lab3.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;


    @PostMapping("/getAllCustomer")
    @ResponseBody
    public ResultMSG getAllCus(@RequestParam(value = "pn",defaultValue = "1") Integer pageNumber) {
        System.out.println("pageNumber = " + pageNumber);
        //引入pagehelper分页插件
        //在查询之前只需要调用,传入页码,每页的大小
        PageHelper.startPage(pageNumber, 5);
        //然后进行分页查询
        List<Customer> allCustomer = customerService.findAllCustomer();
        //使用pageinfo包装查询的结果
        //封装了详细的页面信息,如查询的数据,当前页数
        PageInfo pageInfo = new PageInfo(allCustomer, 5);

        ResultMSG success = ResultMSG.success();
        success.put("pageInfo", pageInfo);
        return success;
    }

}
