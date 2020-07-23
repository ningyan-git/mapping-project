package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.DeptMapper;
import com.aaa.eleven.model.Dept;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 部门增删改查
 */
@Service
public class DeptService  extends BaseService<Dept> {
    @Autowired
    private DeptMapper deptMapper;
    /**
     * 批量删除部门信息
     * @param ids
     * @return
     */

    public Integer deleteBatchByIds(List<Integer> ids){
        return deptMapper.deleteBachById(ids);
    }
    /**
     * 查询部门
     * @return
     */
    public List<Map<String, Object>> selectAllDept(int curpage,int pagesize,String deptName){

        PageHelper.startPage(curpage, pagesize);
        List<Map<String, Object>> depts = deptMapper.selectAllDept(deptName);
        PageInfo<Map> mapPageInfo = new PageInfo(depts);
        if (depts !=null) {
            return depts;
        }else {
            return null;
        }
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    public Boolean addDept(Dept dept){
        // 尽量使用包装类 Boolean
        // 判断dept是不是拿到

        if (dept !=null &&dept.getParentId() !=null && dept.getDeptName() != null){
            int insert = deptMapper.insert(dept);
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
     * 更新部门
     * @param dept
     * @return
     */
    public Boolean updateDept(Dept dept){
        if (dept !=null && dept.getDeptId() !=null){
            int i = deptMapper.updateByPrimaryKey(dept);
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
     * 逻辑删除
     * @return
     */
    public Boolean deleteDept(Dept dept){
        if (dept !=null&& dept.getDeptId() !=null) {
            int delete = deptMapper.delete(dept);
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
