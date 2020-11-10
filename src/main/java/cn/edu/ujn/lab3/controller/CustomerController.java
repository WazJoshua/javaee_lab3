package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.dao.BaseDictMapper;
import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.model.CustomerWithPageNumber;
import cn.edu.ujn.lab3.model.ResultMSG;
import cn.edu.ujn.lab3.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/7
 */
@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    BaseDictMapper baseDictMapper;

    Gson gson = new Gson();


    @PostMapping("/getAllCustomer")
    @ResponseBody
    public ResultMSG getAllCus(@RequestParam(value = "pn", defaultValue = "1") Integer pageNumber) {
        System.out.println("pageNumber = " + pageNumber);
        //引入pagehelper分页插件
        //在查询之前只需要调用,传入页码,每页的大小
        PageHelper.startPage(pageNumber, 5);
        //然后进行分页查询
        List<Customer> allCustomer = customerService.findAllCustomer();
        for (Customer c :
                allCustomer) {
            System.out.println("c = " + c);
        }
        //使用pageinfo包装查询的结果
        //封装了详细的页面信息,如查询的数据,当前页数
        PageInfo pageInfo = new PageInfo(allCustomer, 5);
        System.out.println("pageInfo = " + pageInfo);
        ResultMSG success = ResultMSG.success();
        success.put("pageInfo", pageInfo);
        return success;
    }

    @PostMapping("/getCustomerBySel")
    @ResponseBody
    public ResultMSG getCusBySel(@RequestBody String customerAndPage) {
        //System.out.println("customerAndPage = " + customerAndPage);
        CustomerWithPageNumber customerWithPageNumber = gson.fromJson(customerAndPage, new TypeToken<CustomerWithPageNumber>() {
        }.getType());
        //System.out.println("customerWithPageNumber =================== " + customerWithPageNumber);
        Customer customer = customerWithPageNumber.getCustomer();
        int pageNumber = customerWithPageNumber.getPn();
        PageHelper.startPage(pageNumber, 5);
        List<Customer> customers = customerService.selectCusBySel(customer);

        PageInfo pageInfo = new PageInfo(customers, 5);
        List<Customer> nCustomers = new ArrayList<Customer>();
        for (Customer c :
                customers) {
            BaseDict cusSource = baseDictMapper.selectByPrimaryKey(c.getCustSource());
            BaseDict cusIndustry = baseDictMapper.selectByPrimaryKey(c.getCustIndustry());
            BaseDict cusLevel = baseDictMapper.selectByPrimaryKey(c.getCustLevel());
            c.setCustSource(cusSource.getDictItemName());
            c.setCustIndustry(cusIndustry.getDictItemName());
            c.setCustLevel(cusLevel.getDictItemName());
            nCustomers.add(c);
        }
        pageInfo.setList(nCustomers);

        ResultMSG success = ResultMSG.success();
        success.put("pageInfo", pageInfo);
        return success;
    }


    @PostMapping("/createNewCus")
    @ResponseBody
    public ResultMSG createNewCus(@RequestBody Customer customer) {
        System.out.println("customer = " + customer);
        boolean b = customerService.insertCustomer(customer);
        if (b) {
            return ResultMSG.success();
        }
        return ResultMSG.error();
    }

    @PostMapping("/getCustomerById")
    @ResponseBody
    public ResultMSG getCusById(@RequestParam(value = "id") Integer id) {
        System.out.println("id = " + id);
        Customer customer = new Customer();
        customer.setCustId(id);
        System.out.println("customer = " + customer);
        List<Customer> customers = customerService.selectCusBySel(customer);
        if (customers.get(0) == null) {
            return ResultMSG.error();
        }
        Customer resultCus = customers.get(0);
        System.out.println("resultCus = " + resultCus);
        ResultMSG success = ResultMSG.success();
        success.put("customer", resultCus);
        return success;
    }

    @PostMapping("/updateCusById")
    @ResponseBody
    public ResultMSG updateCusById(@RequestBody Customer customer) {
        boolean b = customerService.updateCusById(customer);
        if (b) {
            return ResultMSG.success();
        } else {
            return ResultMSG.error();
        }
    }

}
