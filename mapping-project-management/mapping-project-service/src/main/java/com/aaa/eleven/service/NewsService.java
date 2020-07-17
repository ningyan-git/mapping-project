package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.NewsMapper;
import com.aaa.eleven.model.Dict;
import com.aaa.eleven.model.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 12:49
 * @Description
 */
@Service
public class NewsService extends BaseService<News> {
    @Autowired
    private NewsMapper newsMapper;
    /**
     * 查询
     * @return
     */
    public List<News> selectAllNews(int curpage,int pagesize,String title){
        PageHelper.startPage(curpage, pagesize);
        List<News> news = newsMapper.selectAllNews(title);
        System.out.println(news.toString());
        PageInfo<Map> mapPageInfo = new PageInfo(news);
        if (news.size()>0){
            return news;
        }else {
            return null;
        }

    }

    /**
     * 添加
     * @param
     * @return
     */
    public Boolean addNews(News news){
        // 尽量使用包装类 Boolean
        // 判断dept是不是拿到

        if (news !=null){
            int insert = newsMapper.insert(news);
            if (insert >0){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 更新
     * @param
     * @return
     */
    public Boolean updateNews(News news){
        if (news !=null){
            int i = newsMapper.updateByPrimaryKey(news);
            if (i >0){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    /**
     * 删除
     * @return
     */
    public Boolean deleteNews(News news){
        if (news !=null) {
            int delete = newsMapper.delete(news);
            if (delete >0){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
}
