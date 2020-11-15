package cn.edu.ujn.lab3.controller;

import cn.edu.ujn.lab3.model.BaseDict;
import cn.edu.ujn.lab3.model.DictWithPageNumber;
import cn.edu.ujn.lab3.model.ResultMSG;
import cn.edu.ujn.lab3.service.IBaseDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
@Controller
public class BaseDictController {

    @Autowired
    IBaseDictService baseDictService;

    Gson gson = new Gson();

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

    @PostMapping("/getdictItemName")
    @ResponseBody
    public ResultMSG dictItemName(@RequestBody String dictTypeCode) {
        System.out.println(dictTypeCode);
        List<BaseDict> sources = baseDictService.findDicByDicType(dictTypeCode);
        ResultMSG success = ResultMSG.success();
        success.put("sources", sources);
        return success;
    }

    @PostMapping("/getdictTypeName")
    @ResponseBody
    public ResultMSG dictTypeName() {
        List<BaseDict> sources = baseDictService.findAllTypeName();
        ResultMSG success = ResultMSG.success();
        success.put("sources", sources);
        return success;
    }

    @PostMapping("/createSource")
    @ResponseBody
    public ResultMSG addSource(@RequestBody BaseDict baseDict) {
        System.out.println(baseDict);
        baseDict.setDictEnable("1");
        baseDict.setDictTypeCode("001");
        baseDict.setDictTypeName("客户行业");
        baseDict.setDictSort(baseDictService.selectSortMaximum() + 1);
        baseDict.setDictId(Integer.toString(baseDictService.selectIdMaximum() + 1));
        boolean b = baseDictService.insertDictSource(baseDict);
        System.out.println(b);
        if (b) {
            return ResultMSG.success();
        } else return ResultMSG.error();
    }

    @PostMapping("/getDictBySel")
    @ResponseBody
    public ResultMSG getDictBySel(@RequestBody String dictAndPage) {
        System.out.println("dictAndPage = " + dictAndPage);
        DictWithPageNumber dictWithPageNumber = gson.fromJson(dictAndPage,new TypeToken<DictWithPageNumber>(){}.getType());
        System.out.println(dictWithPageNumber);
        BaseDict baseDict = dictWithPageNumber.getBaseDict();
        System.out.println(baseDict);
        int pageNumber = dictWithPageNumber.getPage();
        System.out.println(pageNumber);
//        PageHelper.startPage(pageNumber,5);
//        List<BaseDict> baseDicts = baseDictService.findDictBySel(baseDict);
//        System.out.println(baseDicts);
//        PageInfo pageInfo = new PageInfo(baseDicts,5);
//        pageInfo.setList(baseDicts);
        ResultMSG success = ResultMSG.success();
//        success.put("pageInfo",pageInfo);
        return success;
    }


}
