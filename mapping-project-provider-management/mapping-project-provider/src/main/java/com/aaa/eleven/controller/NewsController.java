package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Dict;
import com.aaa.eleven.model.News;
import com.aaa.eleven.service.NewsService;
import com.aaa.eleven.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 12:55
 * @Description
 */
@RestController
@RequestMapping("/news")
public class NewsController extends CommonController<News> {

    @Autowired
    private NewsService newsService;
    @Override
    public BaseService<News> getBaseService() {
        return newsService;
    }
    /**
     * 批量删除新闻信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatchNewsByIds")
    public ResultData deleteBatchNewsByIds(@RequestBody List<Integer> ids){
        Integer deleteBatchByIds = newsService.deleteBatchByIds(ids);
        if (deleteBatchByIds>0){
            return deleteSuccess("批量删除成功");
        }
        return deleteFailed("批量删除失败");
    }
    @GetMapping("/selectAll")
    public ResultData selectAllNews(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage , @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam String title){

        List<News> news = newsService.selectAllNews(curpage, pagesize, title);
        if (news.size()>0){
            return selectSuccess(news);
        }
        return selectFailed();
    }
    @PostMapping("/addNews")
    public ResultData addNews(@RequestBody News news){
        Boolean aBoolean = newsService.addNews(news);

        if (aBoolean){
            return insertSuccess(aBoolean);
        }
        return insertFailed();
    }
    @RequestMapping("/updateNews")
    public ResultData updateNews(@RequestBody News news){

        Boolean aBoolean = newsService.updateNews(news);
        if (aBoolean){
            return updateSuccess(aBoolean);
        }
        return updateFailed();
    }
    @RequestMapping("/deleteNews")
    public ResultData deleteNews(@RequestBody News news){
        Boolean aBoolean = newsService.deleteNews(news);
        if (aBoolean){
            return deleteSuccess(aBoolean);
        }
        return deleteFailed();
    }

}
