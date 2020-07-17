package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.model.Dict;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 11:29
 * @Description
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private MappingProjectService mappingProjectService;

    /**
     * 查询所有字典
     * @param curpage
     * @param pagesize
     * @param tableName
     * @param fieldName
     * @param keyy
     * @return
     */
    @GetMapping("/selectAll")
    public ResultData selectAllDict(@RequestParam int curpage , @RequestParam int pagesize, @RequestParam String tableName, @RequestParam String fieldName, @RequestParam String keyy){
        ResultData resultData = mappingProjectService.selectAllDict(curpage, pagesize, tableName, fieldName, keyy);
        return resultData;
    }

    /**
     * 添加字典
     * @param dict
     * @return
     */
    @PostMapping("/addDict")
    public ResultData addDict(@RequestBody Dict dict){
        ResultData resultData = mappingProjectService.addDict(dict);
        return resultData;
    }

    /**
     * 根据id更新字典
     * @param dict
     * @return
     */
    @RequestMapping("/updateDict")
    public ResultData updateDict(@RequestBody Dict dict){
        ResultData aBoolean = mappingProjectService.updateDict(dict);
        return aBoolean;
    }

    /**
     * 删除
     * @param dict
     * @return
     */
    @RequestMapping("/deleteDict")
    public ResultData deleteDict(@RequestBody Dict dict){
        ResultData aBoolean = mappingProjectService.deleteDict(dict);
        return aBoolean;
    }
}
