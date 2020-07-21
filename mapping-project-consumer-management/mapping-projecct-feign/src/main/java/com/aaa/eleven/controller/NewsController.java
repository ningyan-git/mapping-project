package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.model.News;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 13:03
 * @Description
 */
@RestController
@RequestMapping("/news")
public class NewsController {

   @Autowired
    private MappingProjectService mappingProjectService;

    /**
     * 批量删除新闻信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatchNewsByIds")
    public ResultData deleteBatchNewsByIds(@RequestBody List<Integer> ids){
        return mappingProjectService.deleteBatchNewsByIds(ids);
    }
    @GetMapping("/selectAll")
    public ResultData selectAllNews(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam String title){
        return mappingProjectService.selectAllNews(curpage, pagesize, title);

    }
    @PostMapping("/addNews")
    public ResultData addNews(@RequestBody News news){
        return mappingProjectService.addNews(news);

    }
    @RequestMapping("/updateNews")
    public ResultData updateNews(@RequestBody News news){
        return mappingProjectService.updateNews(news);

    }
    @RequestMapping("/deleteNews")
    public ResultData deleteNews(@RequestBody News news){
        return mappingProjectService.deleteNews(news);

    }
}
