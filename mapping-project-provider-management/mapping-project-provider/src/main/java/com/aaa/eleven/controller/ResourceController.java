package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Resource;
import com.aaa.eleven.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 11:13
 * @Description
 */
@RestController
public class ResourceController extends CommonController<Resource> {
    @Autowired
    private ResourceService resourceService;
    @Override
    public BaseService<Resource> getBaseService() {
        return resourceService;
    }

    /**
     *  根据 refBizId 查询信息 返回 测绘成果及档案管理、质量体系、通用材料汇总
     * @param map
     * @return
     */
    @GetMapping("/resource/selectResourceByrefBizId")
    public ResultData selectResourceByrefBizId(@RequestParam Map map){
        if(map.get("refBizId") != null){
            List<Resource> resourceList = resourceService.selectResourceList(Long.parseLong(map.get("refBizId").toString()));
            if(resourceList != null){
                return selectSuccess(resourceList);
            }

        }
        return selectFailed();
    }
}
