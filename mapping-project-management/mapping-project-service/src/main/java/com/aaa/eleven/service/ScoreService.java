package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.ftp.UploadService;
import com.aaa.eleven.mapper.ScoreMapper;
import com.aaa.eleven.model.FtpFile;
import com.aaa.eleven.model.MappingUnit;
import com.aaa.eleven.model.Resource;
import com.aaa.eleven.model.Score;
import com.aaa.eleven.utils.FileNameUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.aaa.eleven.staticproperties.RedisProperties.POINT;
import static com.aaa.eleven.staticproperties.ScoreProperties.*;


/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/17 11:46
 * @Description
 */
@Service
public class ScoreService extends BaseService<Score> {
    @Autowired
    private ScoreMapper scoreMapper;
    /***
     * @Author ftt
     * @Description
     * 查询分值变化
     * @Date 2020/7/17 11:51
     * @Param [unitId]
     * @return java.util.List<com.aaa.eleven.model.Score>
     */
    public PageInfo<Score> selectScoreList(Map map){
        if(null != map.get("unitId")){
            int  pageNum = Integer.parseInt(map.get("pageNum").toString());
            int  pageSize = Integer.parseInt(map.get("pageSize").toString());
            PageHelper.startPage(pageNum,pageSize);
            Score score = new Score();
            score.setUnitId(Long.parseLong(map.get("unitId").toString()));
            List<Score> scores = selectList(score);
            if(scores != null && scores.size() > 0){
                PageInfo<Score> pageInfo = new PageInfo<Score>(scores);
                return pageInfo;
            }
        }
        return null;
    }
    /**
     * 调整分值
     * 加减分 score + -
     * mapping-unit id -->score字段
     * resource
     */
    public Boolean updateScore(Map map, MultipartFile file,  UploadService uploadService, ResourceService resourceService, MappingUnitService mappingUnitService){
        if(map.get("unit_id") != null){
            long id = Long.parseLong(map.get("unit_id").toString());
            //对象
            Score score = new Score();
            //加减分
            int scoreValue1 = Integer.parseInt(map.get(SCORE1).toString());
            //单位表的score值
            int scoreValue2 = 0;
            if(map.get(SCORE2) != null){
                scoreValue2 = Integer.parseInt(map.get(SCORE2).toString());
            }else {
                return false;
            }
            if(PLUS.equals(map.get("sign"))){
                score.setScorePlus(scoreValue1);
                scoreValue2 += scoreValue1;

            }else if(SUBTRACT.equals(map.get("sign"))){
                score.setScoreSubtract(scoreValue1);
                scoreValue2 -= scoreValue1;
            }else {
                return false;
            }
            score.setCreateTime(new Date());
            score.setUnitId(id);
            if(map.get(REASON) != null){
                score.setReason(map.get(REASON).toString());
            }
            score.setId(Long.parseLong(FileNameUtils.getFileName()));
            Integer i1 = insert(score);
            //修改mapping_unit表的数据
            MappingUnit mappingUnit = new MappingUnit();
            mappingUnit.setId(id);
            mappingUnit.setScore(scoreValue2);
            //判断黑白名单
            if(scoreValue2 > 100){
                mappingUnit.setUnitStatus(1);
            }else if(scoreValue2 < 60){
                mappingUnit.setUnitStatus(2);
            }else {
                mappingUnit.setUnitStatus(3);
            }
            Integer i2 = mappingUnitService.update(mappingUnit);
            Resource resource1 = new Resource();
            //文件上传
            if(file != null ){
                FtpFile ftpFile = uploadService.upload(file, null);
                resource1.setId(Long.parseLong(FileNameUtils.getFileName()));
                resource1.setRefBizId(id);
                resource1.setRefBizType("附件");
                resource1.setPath(ftpFile.getFilePath());
                resource1.setCreateTime(new Date());
                resource1.setName(ftpFile.getFileName());
                resource1.setExtName(ftpFile.getFileName().substring(ftpFile.getFileName().lastIndexOf(POINT)));
            }
            //新增resource表
            resourceService.insert(resource1);
            if(i1 > 0 && i2 > 0){
                return true;
            }
        }
        return false;
    }
}
