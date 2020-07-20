package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.mapper.SpecialPostMapper;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:36
 * @Description
 */
@Service
public class SpecialPostService extends BaseService<SpecialPost> {
    @Autowired
    private SpecialPostMapper specialPostMapper;
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员
     *       把userId放入一个对象中，根据这个对象查询特殊岗位人员
     * @Date 2020/7/14 21:38
     * @Param [userId]
     * @return com.aaa.eleven.model.SpecialPost
     */
    public PageInfo selectOne(Integer pageNo, Integer pageSize, Long userId){
        PageHelper.startPage(pageNo,pageSize);
        SpecialPost specialPost = new SpecialPost();
        if(null !=userId && !"".equals(userId)){
            specialPost.setUserId(userId);
            List<SpecialPost> select = specialPostMapper.select(specialPost);
            if(0 < select.size()){
                PageInfo pageInfo = new PageInfo(select);
                return pageInfo;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 根据id查询特殊岗位人员的详细信息
     * @Date 2020/7/16 11:49
     * @Param [id]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public  List<Map<String,Object>> selectSpecialPostDetail(Long id){
        if(null != id){
            List<Map<String, Object>> maps = specialPostMapper.selectSpecialPostDetail(id);
            if(null != maps && maps.size() > 0){
                return maps;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 新增特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean insertSpecialPost( SpecialPost specialPost){
        if(specialPost != null){
            specialPost.setId(Long.parseLong(FileNameUtils.getFileName()));
            specialPost.setCreateTime(new Date());
            Integer i = insert(specialPost);
            if(i > 0){
                return  true;
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 修改特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean updateSpecialPost(SpecialPost specialPost){
        if(specialPost != null){
            if(specialPost.getId() != null){
                specialPost.setModifyTime(new Date());
                Integer i = update(specialPost);
                if(i > 0){
                    return  true;
                }
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 删除特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    public Boolean deleteSpecialPost(SpecialPost specialPost){
        if(specialPost != null){
            if(specialPost.getId() != null){
                Integer i = delete(specialPost);
                if(i > 0){
                    return  true;
                }
            }
        }
        return false;
    }
}
