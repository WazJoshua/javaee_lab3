package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.model.Customer;
import cn.edu.ujn.lab3.model.ResultMSG;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:Joshua
 * @Date:2020/11/4
 */
@Controller
public class TestController {

    //@GetMapping("/")
    public String test01() {
        return "/pages/index";
    }

    @PostMapping("/testJson")
    @ResponseBody
    public ResultMSG testJson(@RequestBody String customer) {
        System.out.println("customer = " + customer);
        return ResultMSG.success();
    }
}
