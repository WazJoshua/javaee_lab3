package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.ResultMSG;
import cn.edu.ujn.lab3.service.IBaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
@Controller
public class BaseDictController {

    @Autowired
    IBaseDictService baseDictService;

    @PostMapping("/getcustomerFrom")
    @ResponseBody
    public ResultMSG cusSource() {
        List<BaseDict> sources = baseDictService.findDicByDicType("002");
        ResultMSG success = ResultMSG.success();
        success.put("sources", sources);
        return success;
    }

    @PostMapping("/getcustIndustry")
    @ResponseBody
    public ResultMSG cusIndustry() {
        List<BaseDict> sources = baseDictService.findDicByDicType("001");
        ResultMSG success = ResultMSG.success();
        success.put("sources", sources);
        return success;
    }

    @PostMapping("/getcustLevel")
    @ResponseBody
    public ResultMSG cusLevel() {
        List<BaseDict> sources = baseDictService.findDicByDicType("006");
        ResultMSG success = ResultMSG.success();
        success.put("sources", sources);
        return success;
    }



}
