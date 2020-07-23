package com.aaa.eleven.service;

import com.aaa.eleven.base.BaseService;
import com.aaa.eleven.ftp.UploadService;
import com.aaa.eleven.mapper.AuditMapper;
import com.aaa.eleven.mapper.MappingProjectMapper;
import com.aaa.eleven.mapper.ResultCommitMapper;
import com.aaa.eleven.model.*;
import com.aaa.eleven.utils.FileNameUtils;
import com.aaa.eleven.vo.MappingProjectAndResultCommitVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

import static com.aaa.eleven.staticproperties.RedisProperties.POINT;
import static com.aaa.eleven.staticproperties.TimeForatProperties.TIME_FORMAT;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/16 10:25
 * @Description
 */
@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;
    @Autowired
    private ResultCommitMapper resultCommitMapper;
    @Autowired
    private AuditMapper auditMapper;
    /***
     * @Author ftt
     * @Description
     * 分页查询 MappingProject列表
     * @Date 2020/7/16 10:30
     * @Param [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    public PageInfo selectMappingProjectList(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();
        if(0 < mappingProjects.size()){
            PageInfo pageInfo = new PageInfo(mappingProjects);
            return pageInfo;
        }
        return null;
    }
    /***
     * @Author ftt
     * @Description
     * 根据userid查询项目 + 项目类型
     * @Date 2020/7/18 10:11
     * @Param [map]
     * @return com.github.pagehelper.PageInfo<com.aaa.eleven.model.MappingProject>
     */
    public PageInfo<MappingProject> selectByUserId(Map map) {
        //获取分页信息
        Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        //获取查询条件
        MappingProject project = new MappingProject();
        Long userId = Long.parseLong(map.get("userId").toString());
        String projectType = "";
        if(map.get("projectType") != null ){
            projectType =map.get("projectType").toString();
        }
        if (projectType == null && "".equals(projectType)) {
            project.setUserId(userId);
        }else {
            project.setUserId(userId).setProjectType(projectType);
        }
        //进行查询
        List<MappingProject> projects = mappingProjectMapper.select(project);
        //判断查询结果是否为空
        if (projects.size() > 0) {
            return new PageInfo(projects);
        }
        return null;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 项目管理中的操作
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:57
     */
    /**
     * 功能描述: <br>
     *@Description
     * 条件 分页查询
     * @Param: [currentPage, pageSize, project_type]
     * @Return: com.github.pagehelper.PageInfo
     * @Author: zh
     * @Date: 2020/7/16 0016 16:59
     */
    public PageInfo query(Integer currentPage, Integer pageSize, String project_type) {
        PageHelper.startPage(currentPage,pageSize);
        List<Map> query = mappingProjectMapper.query(project_type);
        PageInfo<Map> pageInfo=new PageInfo<Map>(query);
        return pageInfo;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 查询所有类型
     * @Param: []
     * @Return: java.util.List
     * @Author: zh
     * @Date: 2020/7/16 0016 16:59
     */
    public List<Map> queryAllProject_Type(){
        return mappingProjectMapper.queryAllProject_Type();
    };
    /**
     * 功能描述: <br>
     *@Description
     * 同时增加t_mapping_project t_audit、t_result_commit表的数据文件上传到t_resource
     * @Param: [mappingProjectAndResultCommitVo]
     * @Return: java.util.List
     * @Author: zh
     * @Date: 2020/7/16 0016 22:29
     */
    public int insertMappingProjectAndResultCommit(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo, UploadService uploadService,ResourceService resourceService){
        if(mappingProjectAndResultCommitVo !=null){
            //获取封装后的MappingProject、ResultCommit、Audit、Resource 对象
            //判断id为空，为新增，不为空则不进行其他操作
            if(mappingProjectAndResultCommitVo.getMapping_id() == null){
                //新增MappingProject ，自动生成ID
                mappingProjectAndResultCommitVo.setMapping_id(Long.parseLong(FileNameUtils.getFileName()));
                MappingProject mappingProject = setMappingProject(mappingProjectAndResultCommitVo);
                ResultCommit resultCommit = setResultCommit(mappingProjectAndResultCommitVo);
                Audit audit = setAudit(mappingProjectAndResultCommitVo);
                if(mappingProject != null && resultCommit != null && audit != null){
                    //新增项目
                    Integer i1 = mappingProjectMapper.insertMappingProject(mappingProject);
                    //新增commit
                    Integer i2 = resultCommitMapper.insertResultCommit(resultCommit);
                    //新增审核记录
                    int i3 = auditMapper.insert(audit);
                    MultipartFile[] contractFiles = mappingProjectAndResultCommitVo.getContractFile();
                    Long id = mappingProjectAndResultCommitVo.getMapping_id();
                    //新增合同附件
                    for(MultipartFile contractFile: contractFiles ){
                        Resource resource = setResource(contractFile, id, uploadService);
                        if(resource != null){
                            resourceService.insert(resource);
                        }
                    }
                    //新增范围线附件
                    MultipartFile[] rangetFiles = mappingProjectAndResultCommitVo.getRangetFile();
                    for(MultipartFile rangetFile: rangetFiles ){
                        Resource resource = setResource(rangetFile, id, uploadService);
                        if(resource != null){
                            resourceService.insert(resource);
                        }
                    }
                    if(i1 > 0 && i2 > 0 && i3 > 0){
                        return 1;
                    }
                }
            }

        }
        return 0;

    }
    /**
     * 功能描述: <br>
     *@Description
     * 修改mappingProject数据
     * @Param: [mappingProject]
     * @Return: java.lang.Integer
     * @Author: zh
     * @Date: 2020/7/16 0016 20:59
     */
    public int updateMappingProjectById(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo, UploadService uploadService,ResourceService resourceService){
        if(mappingProjectAndResultCommitVo !=null){
            //获取封装后的MappingProject、ResultCommit、Audit、Resource 对象
            //判断id不为空，为修改，为空则不进行其他操作
            if(mappingProjectAndResultCommitVo.getMapping_id() != null){
                MappingProject mappingProject = setMappingProject(mappingProjectAndResultCommitVo);
                ResultCommit resultCommit = setResultCommit(mappingProjectAndResultCommitVo);
                Audit audit = setAudit(mappingProjectAndResultCommitVo);
                if(mappingProject != null && resultCommit != null && audit != null){
                    //修改项目
                    Integer i1 = mappingProjectMapper.updateMappingProjectById(mappingProject);
                    //修改commit
                    Integer i2 = resultCommitMapper.updateResultCommit(resultCommit);
                    //新增审核记录
                    int i3 = auditMapper.insert(audit);
                    //获取到用户修改附件之前的id，为了先删除resource表的数据，然后进行插入
                    Long[] resourceIds = mappingProjectAndResultCommitVo.getResourceIds();
                    //遍历之前的resourceID ，在resource 进行删除
                    if(resourceIds != null && resourceIds.length > 0){
                        for(Long id : resourceIds){
                            Resource resource = new Resource();
                            resource.setId(id);
                            resourceService.delete(resource);
                        }
                    }
                    //之前resource表的数据删除之后 ，进行增加
                    MultipartFile[] contractFiles = mappingProjectAndResultCommitVo.getContractFile();
                    Long id = mappingProjectAndResultCommitVo.getMapping_id();
                    //新增合同附件
                    for(MultipartFile contractFile: contractFiles ){
                        Resource resource = setResource(contractFile, id, uploadService);
                        if(resource != null){
                            resourceService.insert(resource);
                        }
                    }
                    //新增范围线附件
                    MultipartFile[] rangetFiles = mappingProjectAndResultCommitVo.getRangetFile();
                    for(MultipartFile rangetFile: rangetFiles ){
                        Resource resource = setResource(rangetFile, id, uploadService);
                        if(resource != null){
                            resourceService.insert(resource);
                        }
                    }
                    if(i1 > 0 && i3 > 0){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 删除
     *  mappingProject resultCommit audit resource
     * @param id
     * @return
     */
    public int deleteMappingProjectAndResult(Long id,ResourceService resourceService){
        if(id != null){
            Integer i1 = mappingProjectMapper.deleteMappingProjectById(id);
            Integer i2 = resultCommitMapper.deleteResultCommitByRefId(id);
            //删除audit表记录
            Audit audit = new Audit();
            audit.setRefId(id);
            int i3 = auditMapper.delete(audit);
            //删除resource记录
            Resource resource = new Resource();
            resource.setRefBizId(id);
            Integer i4 = resourceService.delete(resource);
            return 1;
        }
        return 0;
    }
    /**
     * 功能描述: <br>
     *@Description
     * 主页中的模糊查询
     * @Param:
     * @Return:
     * @Author: zh
     * @Date: 2020/7/17 0017 10:58
     */
    public List<Map<String,Object>> selectMappingProjectByProjectNameAndProjectTypeAndStartDate(String projectName,String projectType,String startDate){
        List<Map<String, Object>> maps = mappingProjectMapper.selectMappingProjectByProjectNameAndProjectTypeAndStartDate(projectName, projectType, startDate);
        if(maps.size()>0)
        {
            return maps;
        }
        else
        {
            return null;
        }
    }
    /**
     * 功能描述: <br>
     *@Description
     * 模糊查询中的下拉框数据 测绘类型
     * @Param: []
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zh
     * @Date: 2020/7/17 0017 14:53
     */
    public List<Map<String,Object>> selectProjectType(){
        List<Map<String, Object>> maps = mappingProjectMapper.selectProjectType();
        if(maps.size()>0)
        {
            return maps;
        }
        else
        {
            return null;
        }
    }

    /***
     * @Author ftt
     * @Description
     * 通过MappingProjectAndResultCommit传参，返回MappingProject实体类
     * @Date 2020/7/20 22:14
     * @Param [mappingProjectAndResultCommitVo]
     * @return com.aaa.eleven.model.MappingProject
     */
    private MappingProject setMappingProject(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        MappingProject mappingProject = new MappingProject();
        if(mappingProjectAndResultCommitVo !=null) {
            //判断 id 不为空
            if (mappingProjectAndResultCommitVo.getMapping_id() != null) {
                mappingProject.setId(mappingProjectAndResultCommitVo.getMapping_id());
                mappingProject.setProjectType(mappingProjectAndResultCommitVo.getMapping_projectType());
                mappingProject.setProjectName(mappingProjectAndResultCommitVo.getMapping_projectName());
                mappingProject.setProjectLeader(mappingProjectAndResultCommitVo.getMapping_projectLeader());
                mappingProject.setStartDate(mappingProjectAndResultCommitVo.getMapping_startDate());
                mappingProject.setEndDate(mappingProjectAndResultCommitVo.getMapping_endDate());
                mappingProject.setAcceptanceDepartment(mappingProjectAndResultCommitVo.getMapping_acceptanceDepartment());
                mappingProject.setProjectAmount(mappingProjectAndResultCommitVo.getMapping_projectAmount());
                mappingProject.setSchedule(mappingProjectAndResultCommitVo.getMapping_schedule());
            }
        }
        return mappingProject;
    }

    /***
     * @Author ftt
     * @Description
     * 通过MappingProjectAndResultCommit传参，返回ResultCommit实体类
     * @Date 2020/7/20 22:20
     * @Param [mappingProjectAndResultCommitVo]
     * @return com.aaa.eleven.model.ResultCommit
     */
    private ResultCommit setResultCommit(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
        ResultCommit resultCommit = new ResultCommit();
        if(mappingProjectAndResultCommitVo !=null) {
            //判断 id 不为空
            if (mappingProjectAndResultCommitVo.getMapping_id() != null) {
                resultCommit.setPlottingScale(mappingProjectAndResultCommitVo.getResult_plotting_scale());
                resultCommit.setMediumType(mappingProjectAndResultCommitVo.getResult_medium_type());
                resultCommit.setResultDate(mappingProjectAndResultCommitVo.getResult_date());
                resultCommit.setName(mappingProjectAndResultCommitVo.getResult_name());
                resultCommit.setCreateDate(mappingProjectAndResultCommitVo.getResult_create_date());
                if(mappingProjectAndResultCommitVo.getResult_id() != null){
                    resultCommit.setId(mappingProjectAndResultCommitVo.getResult_id());
                }else {
                    resultCommit.setId(Long.parseLong(FileNameUtils.getFileName()));
                }
                //判断mappingProjectAndResultCommitVo.getMapping_id()是否为空，为空再次生成id，不为空 set
                if(mappingProjectAndResultCommitVo.getMapping_id() != null){
                    resultCommit.setRefId(mappingProjectAndResultCommitVo.getMapping_id());
                }else{
                    resultCommit.setRefId(Long.parseLong(FileNameUtils.getFileName()));
                }
            }
        }
        return resultCommit;
    }
    /***
     * @Author ftt
     * @Description
     * 通过MappingProjectAndResultCommit传参，返回Audit实体类
     * @Date 2020/7/20 22:29
     * @Param [mappingProjectAndResultCommitVo]
     * @return com.aaa.eleven.model.Audit
     */
   private Audit setAudit(MappingProjectAndResultCommitVo mappingProjectAndResultCommitVo){
       Audit audit=new Audit();
       if(mappingProjectAndResultCommitVo.getMapping_id() != null){
           audit.setId(Long.parseLong(FileNameUtils.getFileName()));
           audit.setSubmitTime(new Date());
           audit.setRefId(mappingProjectAndResultCommitVo.getMapping_id());
           audit.setStatus(2);
           audit.setName("项目登记审核");
           audit.setType(1);
           audit.setAuditTime(new Date());
           audit.setCreateTime(new Date());
       }
       return audit;
   }
   /***
    * @Author ftt
    * @Description
    * 通过MappingProjectAndResultCommit传参，返回Resource实体类
    * @Date 2020/7/20 22:30
    * @Param [mappingProjectAndResultCommitVo]
    * @return com.aaa.eleven.model.Resource
    */
   private Resource setResource(MultipartFile File,Long id,UploadService uploadService){
       Resource resource1 = new Resource();
       if(File != null ){
           FtpFile upload1 = uploadService.upload(File,null);
           resource1.setId(Long.parseLong(FileNameUtils.getFileName()));
           resource1.setRefBizId(id);
           resource1.setRefBizType("附件");
           resource1.setPath(upload1.getFilePath());
           resource1.setCreateTime(new Date());
           resource1.setName(upload1.getFileName());
           resource1.setExtName(upload1.getFileName().substring(upload1.getFileName().lastIndexOf(POINT)));
       }
       return resource1;
   }

    /**
     * 根据项目 id查询项目详细
     */
    public MappingProject  selectProjectByID(String id){
        if (id !=null){
            MappingProject mappingProject = mappingProjectMapper.selectByPrimaryKey(id);
            if (mappingProject !=null){
                return mappingProject;
            }else {
                return null;
            }
        }
        return null;

    }

    /**
     * 根据项目名字模糊查询并分页
     * @param projectName
     * @return
     */
    public PageInfo<Map> selectAllMappingProject( String projectName,int curpage,int pagesize){
        if (projectName !=null){
            PageHelper.startPage(curpage, pagesize);
            List<MappingProject> mappingProjects = mappingProjectMapper.selectAllMappingProject(projectName);
            PageInfo<Map> mapPageInfo = new PageInfo(mappingProjects);
            if (mappingProjects !=null){
                return mapPageInfo;
            }
            return null;
        }
        return null;
    }
    /**
     * 根据项目id查询审核记录
     */
    public PageInfo<Map> selectAuditRecords(String id,int curpage,int pagesize){
        if (id !=null){
            PageHelper.startPage(curpage, pagesize);
            Example example = new Example(Audit.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("refId",id);
            List<Audit> audits = auditMapper.selectByExample(example);
            PageInfo<Map> mapPageInfo = new PageInfo(audits);
            if (audits !=null){
                return mapPageInfo;
            }else {
                return null;
            }
        }
        return null;
    }

    /**
     * 汇交成果信息，的详情，根据项目id查询相应的项目信息，资源表，汇交结果表
     * 首先查询出项目的汇交成果状态为0通过的项目
     */
    //projectName有默认值
    public PageInfo<Map> selectResultZeroPass(Integer curpage,Integer pagesize,String projectName){

        PageHelper.startPage(curpage, pagesize);
        List<Map<String, Object>> select = mappingProjectMapper.selectResultZeroPass(projectName);
        PageInfo<Map> mapPageInfo = new PageInfo(select);
        if (select !=null){
            return mapPageInfo;
        }
        return null;
    }


    /**
     * 汇交成果信息，的详情，根据项目id查询相应的项目信息，资源表，汇交结果表
     * @param id
     * @return
     */
    public HashMap selectResourceAndResult(String id){
        if (id !=null){
            List<Map<String, Object>> maps = mappingProjectMapper.selectResource(id);
            List<Map<String, Object>> maps1 = mappingProjectMapper.selectResult(id);
            HashMap hashMap = new HashMap();
            hashMap.put("附件",maps);
            hashMap.put("汇交结果",maps1);
            if (maps !=null && maps1 !=null){
                return hashMap;
            }else {
                return null;
            }
        }
        return null;
    }


    /**
     * 项目审核，首先查出项目审核状态为已提交audit_status=2的项目
     */                                                               //projectName有默认值
    public PageInfo<Map> selectAuditCommit(int curpage,int pagesize,String projectName){
        PageHelper.startPage(curpage, pagesize);
        List<Map<String, Object>> maps = mappingProjectMapper.selectAuditCommit(projectName);
        PageInfo<Map> mapPageInfo = new PageInfo(maps);
        if (maps !=null){
            return mapPageInfo;
        }
        return null;
    }

    /**
     * 项目审核
     */
    public boolean projectAudit(HashMap hashMap){

        if (hashMap.get("id") !=null){
            //项目id
            Object id1 = hashMap.get("id");
            long id = Long.valueOf(String.valueOf(id1)).longValue();
            MappingProject mappingProject1 = new MappingProject();
            mappingProject1.setId(id);
            mappingProject1.setModifyTime(DateUtils.formatDate(new Date(),TIME_FORMAT));
            Object status = hashMap.get("status");
            int i1 = Integer.parseInt(status.toString());
            mappingProject1.setAuditStatus(i1);

            int i = mappingProjectMapper.updateByPrimaryKeySelective(mappingProject1);

            //生成audit的编号
            Audit audit = new Audit();
            String fileName = FileNameUtils.getFileName();
            long l = Long.parseLong(fileName);
            audit.setId(l);
            //获取当前审核时间
            DateUtils.formatDate(new Date(),TIME_FORMAT);
            audit.setAuditTime(new Date());

            Object userId = hashMap.get("userId");
            long l1 = Long.valueOf(String.valueOf(userId)).longValue();
            audit.setUserId(l1);
            audit.setRefId(id);
            audit.setCreateTime(new Date());
            audit.setStatus(i1);
            Object momo = hashMap.get("momo");
            String s = String.valueOf(momo);
            audit.setMemo(s);
            audit.setName("项目进度审核");
            audit.setType(3);


            int insert = auditMapper.insert(audit);

            if (insert >0 && i >0){
                return true;
            }
        }
        return false;

    }



    /**
     * 成果汇交审核
     * 首先查出项目成果状态为已提交results_status=2的项目
     */                                                           //projectName有默认值
    public PageInfo<Map> selectAuditResult(int curpage,int pagesize, String projectName){
        PageHelper.startPage(curpage, pagesize);
        List<Map<String, Object>> maps = mappingProjectMapper.selectAuditResult(projectName);
        PageInfo<Map> mapPageInfo = new PageInfo(maps);
        if (maps !=null){
            return mapPageInfo;
        }
        return null;
    }

    /**
     * 汇交审核
     */
    public boolean resultAudit(HashMap hashMap){

        if (hashMap.get("id") !=null){
            //项目id
            Object id1 = hashMap.get("id");
            long id = Long.valueOf(String.valueOf(id1)).longValue();

            MappingProject mappingProject1 = new MappingProject();
            mappingProject1.setId(id);
            //mappingProject1.setModifyTime(DateUtils.formatDate(new Date(),TIME_FORMAT));
            Object status = hashMap.get("status");
            int i1 = Integer.parseInt(status.toString());
            mappingProject1.setResultsStatus(i1);

            int i = mappingProjectMapper.updateByPrimaryKeySelective(mappingProject1);

            //生成audit的编号
            Audit audit = new Audit();
            String fileName = FileNameUtils.getFileName();
            long l = Long.parseLong(fileName);
            audit.setId(l);
            //获取当前审核时间
            DateUtils.formatDate(new Date(),TIME_FORMAT);
            audit.setAuditTime(new Date());

            Object userId = hashMap.get("userId");
            long l1 = Long.valueOf(String.valueOf(userId)).longValue();
            audit.setUserId(l1);
            audit.setRefId(id);
            audit.setCreateTime(new Date());
            audit.setStatus(i1);
            Object momo = hashMap.get("momo");
            String s = String.valueOf(momo);
            audit.setMemo(s);
            audit.setType(4);
            audit.setName("项目汇交审核");

            int insert = auditMapper.insert(audit);

            if (insert >0 && i >0){
                return true;
            }
        }
        return false;

    }

}

