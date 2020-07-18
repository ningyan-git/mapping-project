package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.CheckPersonMapper;
import com.aaa.eleven.model.CheckPerson;
import com.aaa.eleven.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 18:35
 * @Description
 */
@Service
public class CheckPersonService extends BaseService<CheckPerson> {
    @Autowired
    private CheckPersonMapper checkPersonMapper;
    /***
     * @Author ftt
     * @Description
     * 查询抽查人员具体信息
     * @Date 2020/7/17 18:39
     * @Param [id]
     * @return com.aaa.eleven.model.CheckPerson
     */
    public CheckPerson selectCheckPersonOne(Long id){
        if(id != null){
            CheckPerson checkPerson = new CheckPerson();
            checkPerson.setId(id);
            CheckPerson checkPerson1 = selectOne(checkPerson);
            if(checkPerson1 != null){
                return checkPerson1;
            }
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 新增抽查人员
     * @Date 2020/7/17 18:42
     * @Param [checkPerson]
     * @return java.lang.Boolean
     */
    public Boolean insertCheckPersonOne(CheckPerson checkPerson){
        if(checkPerson != null){
            checkPerson.setId(Long.parseLong(FileNameUtils.getFileName()));
            Integer i = insert(checkPerson);
            if(i > 0){
                return true;
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 修改抽查人员信息
     * @Date 2020/7/17 18:44
     * @Param [checkPerson]
     * @return java.lang.Boolean
     */
    public Boolean updateCheckPersonOne(CheckPerson checkPerson){
        if(checkPerson != null){
            if(checkPerson.getId() != null){
                Integer i = update(checkPerson);
                if(i > 0){
                    return true;
                }
            }
        }
        return false;
    }
    /***
     * @Author ftt
     * @Description
     * 删除抽查人员信息
     * @Date 2020/7/17 18:45
     * @Param [checkPerson]
     * @return java.lang.Boolean
     */
    public Boolean deleteCheckPersonOne(CheckPerson checkPerson){
        if(checkPerson != null){
            if(checkPerson.getId() != null){
                Integer i = delete(checkPerson);
                if(i > 0){
                    return true;
                }
            }
        }
        return false;
    }
}
