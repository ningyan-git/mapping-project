package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.SpecialPost;
import com.aaa.eleven.service.SpecialPostService;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/14 21:43
 * @Description
 */
@RestController
public class SpecialPostController extends CommonController<SpecialPost> {
    @Autowired
    private SpecialPostService specialPostService;
    @Override
    public BaseService<SpecialPost> getBaseService() {
        return specialPostService;
    }
    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员列表
     * @Date 2020/7/14 21:46
     * @Param [userId]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostListByUserId")
    public ResultData selectSpecialPostListByUserId(@RequestParam(value = "curpage",required = false,defaultValue = "1")int curpage, @RequestParam(value = "pagesize",required = false,defaultValue = "5")int pagesize,@RequestParam("userId") long userId){
        PageInfo pageInfo = specialPostService.selectOne(curpage, pagesize, userId);
        if(pageInfo !=null ){
            return selectSuccess(pageInfo);
        }
        return selectFailed();

    }

    /***
     * @Author ftt
     * @Description
     * 查询特殊岗位人员信息
     * @Date 2020/7/16 12:18
     * @Param [id]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/specialPost/selectSpecialPostDetail")
    public ResultData selectSpecialPostDetail(@RequestParam("id") Long id){
        List<Map<String, Object>> maps = specialPostService.selectSpecialPostDetail(id);
        if(null != maps){
            return selectSuccess(maps);
        }else {
            return  selectFailed();
        }
    }
    /***
     * @Author ftt
     * @Description
     * 新增特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/insertSpecialPost")
    public ResultData insertSpecialPost(@RequestBody SpecialPost specialPost){
        if(specialPost != null){
            specialPost.setId(Long.parseLong(FileNameUtils.getFileName()));
            Integer i = specialPostService.insert(specialPost);
            if(i > 0){
                return  insertSuccess();
            }
        }
        return insertFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 修改特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/updateSpecialPost")
    public ResultData updateSpecialPost(@RequestBody SpecialPost specialPost){
        if(specialPost != null){
          if(specialPost.getId() != null){
              Integer i = specialPostService.update(specialPost);
              if(i > 0){
                  return  updateSuccess();
              }
          }
        }
        return updateFailed();
    }
    /***
     * @Author ftt
     * @Description
     * 删除特殊岗位人员信息
     * @Date 2020/7/16 18:32
     * @Param [specialPost]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/specialPost/deleteSpecialPost")
    public ResultData deleteSpecialPost(@RequestBody SpecialPost specialPost){
        if(specialPost != null){
            if(specialPost.getId() != null){
                Integer i = specialPostService.delete(specialPost);
                if(i > 0){
                    return  deleteSuccess();
                }
            }
        }
        return deleteFailed();
    }
}
