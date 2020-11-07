package cn.edu.ujn.lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author:Joshua
 * @Date:2020/11/4
 */
@Controller
public class TestController {

    @GetMapping("/")
    public String test01(){
        return "/pages/index";
    }
}
