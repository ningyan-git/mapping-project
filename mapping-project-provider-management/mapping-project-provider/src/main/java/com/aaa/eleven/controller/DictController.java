package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.model.Dict;
import com.aaa.eleven.service.DictService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 11:17
 * @Description
 */
@RestController
@RequestMapping("/dict")
public class DictController extends CommonController<Dict> {
    @Autowired
    private DictService dictService;

    @GetMapping("/selectAll")
    public ResultData selectAllDict(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize, @RequestParam String tableName, @RequestParam String fieldName, @RequestParam String keyy){
        List<Dict> dicts = dictService.selectAllDict(curpage, pagesize, tableName, fieldName, keyy);
        if (dicts.size()>0){
            return selectSuccess(dicts);
        }
        return selectFailed();
    }
    @PostMapping("/addDict")
    public ResultData addDict(@RequestBody Dict dict){
        if (dict.getFieldName()==null){
            return insertFailed("fieldName 必须要传参");
        }
        if (dict.getKeyy() ==null){
            return insertFailed("keyy 必须要传参");
        }
        if (dict.getTableName() == null){
            return insertFailed("tableName 必须要传参");
        }
        if (dict.getValuee() == null){
            return insertFailed("valuee 必须要传参");
        }
        Boolean aBoolean = dictService.addDict(dict);
        if (aBoolean){
            return insertSuccess(aBoolean);
        }
        return insertFailed();
    }
    @RequestMapping("/updateDict")
    public ResultData updateDict(@RequestBody Dict dict){
        Boolean aBoolean = dictService.updateDict(dict);
        if (aBoolean){
            return updateSuccess(aBoolean);
        }
        return updateFailed();
    }
    @RequestMapping("/deleteDict")
    public ResultData deleteDict(@RequestBody Dict dict){
        Boolean aBoolean = dictService.deleteDict(dict);
        if (aBoolean){
            return deleteSuccess(aBoolean);
        }
        return deleteFailed();
    }

    @Override
    public BaseService<Dict> getBaseService() {
        return dictService;
    }
}
