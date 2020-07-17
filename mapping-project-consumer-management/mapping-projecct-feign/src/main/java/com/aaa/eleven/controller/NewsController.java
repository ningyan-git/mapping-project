package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.model.News;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 模糊查询，分页新闻
     * @param curpage
     * @param pagesize
     * @param title
     * @return
     */
    @GetMapping("/selectAll")
    public ResultData selectAllNews(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam String title){
        ResultData news = mappingProjectService.selectAllNews(curpage, pagesize, title);
        return news;
    }

    /**
     * 添加
     * @param news
     * @return
     */
    @PostMapping("/addNews")
    public ResultData addNews(@RequestBody News news){
        ResultData aBoolean = mappingProjectService.addNews(news);
        return aBoolean;
    }

    /**
     * 更新
     * @param news
     * @return
     */
    @RequestMapping("/updateNews")
    public ResultData updateNews(@RequestBody News news){
        ResultData aBoolean = mappingProjectService.updateNews(news);
        return aBoolean;
    }

    /**
     * 删除
     * @param news
     * @return
     */
    @RequestMapping("/deleteNews")
    public ResultData deleteNews(@RequestBody News news){
        ResultData aBoolean = mappingProjectService.deleteNews(news);
        return aBoolean;
    }

}
