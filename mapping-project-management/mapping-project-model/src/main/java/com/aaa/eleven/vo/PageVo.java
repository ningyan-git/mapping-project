package com.aaa.eleven.vo;

import com.aaa.eleven.model.MappingProject;

public class PageVo {
    private Integer pageNo;
    private Integer pageSize;
    private MappingProject mappingProject;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public MappingProject getMappingProject() {
        return mappingProject;
    }

    public void setMappingProject(MappingProject mappingProject) {
        this.mappingProject = mappingProject;
    }
}
