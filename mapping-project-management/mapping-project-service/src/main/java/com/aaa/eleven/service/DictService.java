package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.DictMapper;
import com.aaa.eleven.model.Dept;
import com.aaa.eleven.model.Dict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ljy
 * @Date Create in 2020/7/16 11:04
 * @Description
 */
@Service
public class DictService extends BaseService<Dict> {

    @Autowired
    private DictMapper dictMapper;
    /**
     * 查询
     * @return
     */
    public List<Dict> selectAllDict(int curpage,int pagesize,String tableName,String fieldName,String keyy){
        PageHelper.startPage(curpage, pagesize);
        List<Dict> dicts = dictMapper.selectAllDict(tableName,fieldName,keyy);
        PageInfo<Map> mapPageInfo = new PageInfo(dicts);
        if (dicts.size()>0){
            return dicts;
        }else {
            return null;
        }

    }

    /**
     * 添加
     * @param
     * @return
     */
    public Boolean addDict(Dict dict){
        // 尽量使用包装类 Boolean
        // 判断dept是不是拿到

        if (dict !=null && dict.getValuee() !=null && dict.getTableName() !=null && dict.getKeyy()!=null && dict.getFieldName() !=null){
            int insert = dictMapper.insert(dict);
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
    public Boolean updateDict(Dict dict){
        if (dict !=null && dict.getDictId() != null){
            int i = dictMapper.updateByPrimaryKey(dict);
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
    public Boolean deleteDict(Dict dict){
        if (dict !=null && dict.getDictId() != null) {
            int delete = dictMapper.delete(dict);
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
