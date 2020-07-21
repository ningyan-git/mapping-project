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
public class DictController {

    @Autowired
    private MappingProjectService mappingProjectService;
    /**
     * 批量删除字典信息
     * @param ids
     * @return
     */
    @PostMapping("/dict/deleteBatchDictByIds")
    public ResultData deleteBatchDictByIds(@RequestBody List<Integer> ids){
        return mappingProjectService.deleteBatchDictByIds(ids);
    }

    /**
     * 分页 + 模糊 + 查询所有
     * @param curpage
     * @param pagesize
     * @param tableName
     * @param fieldName
     * @param keyy
     * @return
     */
    @GetMapping("/dict/selectAll")
    public ResultData selectAllDict(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize, @RequestParam String tableName, @RequestParam String fieldName, @RequestParam String keyy){
        return mappingProjectService.selectAllDict(curpage,pagesize,tableName,fieldName,keyy);
    }

    /**
     * 新增字典表信息
     * @param dict
     * @return
     */
    @PostMapping("/dict/addDict")
    public ResultData addDict(@RequestBody Dict dict){
        return mappingProjectService.addDict(dict);
    }

    /**
     * 修改字典表
     * @param dict
     * @return
     */
    @RequestMapping("/dict/updateDict")
    public ResultData updateDict(@RequestBody Dict dict){
        return mappingProjectService.updateDict(dict);
    }

    /**
     * 删除字典表信息
     * @param dict
     * @return
     */
    @RequestMapping("/dict/deleteDict")
    public ResultData deleteDict(@RequestBody Dict dict){
        return mappingProjectService.deleteDict(dict);
    }

}
