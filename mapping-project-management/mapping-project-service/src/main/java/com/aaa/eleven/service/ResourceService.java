package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.mapper.ResourceMapper;
import com.aaa.eleven.model.Resource;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 11:07
 * @Description
 */
@Service
public class ResourceService extends BaseService<Resource> {
    @Accessors
    private ResourceMapper resourceMapper;

    /**
     * 根据 refBizId 查询信息
     * @param refBizId
     * @return
     */
    public List<Resource> selectResourceList(Long refBizId){
        if(null != refBizId){
            Resource resource = new Resource();
            resource.setRefBizId(refBizId);
            List<Resource> resources = selectList(resource);
            if(resources != null && resources.size() > 0){
                return resources;
            }
        }
        return null;
    }
}
