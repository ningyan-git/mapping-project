package com.aaa.eleven.mapper;

import com.aaa.eleven.model.News;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NewsMapper extends Mapper<News> {
   public List<News> selectAllNews(@Param("title") String title);
}