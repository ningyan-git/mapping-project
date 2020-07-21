package com.aaa.eleven.controller;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.base.CommonController;
import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.ftp.UploadService;
import com.aaa.eleven.model.Score;
import com.aaa.eleven.service.MappingUnitService;
import com.aaa.eleven.service.ResourceService;
import com.aaa.eleven.service.ScoreService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 14:56
 * @Description
 */
@RestController
public class ScoreController extends CommonController<Score> {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private MappingUnitService mappingUnitService;
    @Autowired
    private UploadService uploadService;
    @Override
    public BaseService<Score> getBaseService() {
        return scoreService;
    }
    /***
     * @Author ftt
     * @Description
     * 查看评分记录
     * @Date 2020/7/17 14:57
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/score/selectScoreList")
    public ResultData selectScoreList(@RequestParam Map map){
        PageInfo<Score> pageInfo = scoreService.selectScoreList(map);
        if(pageInfo != null){
            return selectSuccess(pageInfo);
        }else {
            return selectFailed();
        }
    }

    /***
     * @Author ftt
     * @Description
     * 调整分值
     * @Date 2020/7/17 16:31
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/score/updateScoreMultiTables")
    public ResultData updateScoreMultiTables(@RequestParam Map map, MultipartFile file,String filename){
        Boolean flag = scoreService.updateScore(map,file,filename,uploadService, resourceService, mappingUnitService);
        if(false){
            return updateSuccess();
        }else {
            return updateFailed();
        }
    }
}
