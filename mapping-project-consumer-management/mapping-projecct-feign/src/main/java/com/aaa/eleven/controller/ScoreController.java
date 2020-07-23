package com.aaa.eleven.controller;

import com.aaa.eleven.base.ResultData;
import com.aaa.eleven.model.Score;
import com.aaa.eleven.service.MappingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/21 17:14
 * @Description
 */
@RestController
public class ScoreController {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 查看评分记录
     *      在单位审核的 单位列表的查看评分记录
     * @Date 2020/7/17 14:57
     * @Param []
     * @return com.aaa.eleven.base.ResultData
     */
    @GetMapping("/score/selectScoreList")
    public ResultData selectScoreList(@RequestParam Map map){
      return mappingProjectService.selectScoreList(map);
    }

    /***
     * @Author ftt
     * @Description
     * 调整分值
     *      在单位审核的 单位列表的调整分值
     * @Date 2020/7/17 16:31
     * @Param [map]
     * @return com.aaa.eleven.base.ResultData
     */
    @PostMapping("/score/updateScoreMultiTables")
    public ResultData updateScoreMultiTables(@RequestParam Map map, MultipartFile file,@RequestParam String filename){
        return mappingProjectService.updateScoreMultiTables(map,file,filename);

    }
}
