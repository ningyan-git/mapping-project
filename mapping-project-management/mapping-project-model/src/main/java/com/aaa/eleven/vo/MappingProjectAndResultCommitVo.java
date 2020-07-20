package com.aaa.eleven.vo;

import java.io.Serializable;
import java.util.Date;

public class MappingProjectAndResultCommitVo implements Serializable {
    private  Long mapping_id;
    private String mapping_projectType;
    private String mapping_projectName;
    private String mapping_projectLeader;
    private String mapping_startDate;
    private String mapping_endDate;
    private String mapping_acceptanceDepartment;
    private Double mapping_projectAmount;
    private Integer mapping_schedule;
    private Long result_id;
    private String result_plotting_scale;
    private String result_medium_type;
    private Date result_date;
    private String result_name;
    private Date result_create_date;

    public Long getMapping_id() {
        return mapping_id;
    }

    public void setMapping_id(Long mapping_id) {
        this.mapping_id = mapping_id;
    }

    public String getMapping_projectType() {
        return mapping_projectType;
    }

    public void setMapping_projectType(String mapping_projectType) {
        this.mapping_projectType = mapping_projectType;
    }

    public String getMapping_projectName() {
        return mapping_projectName;
    }

    public void setMapping_projectName(String mapping_projectName) {
        this.mapping_projectName = mapping_projectName;
    }

    public String getMapping_projectLeader() {
        return mapping_projectLeader;
    }

    public void setMapping_projectLeader(String mapping_projectLeader) {
        this.mapping_projectLeader = mapping_projectLeader;
    }

    public String getMapping_startDate() {
        return mapping_startDate;
    }

    public void setMapping_startDate(String mapping_startDate) {
        this.mapping_startDate = mapping_startDate;
    }

    public String getMapping_endDate() {
        return mapping_endDate;
    }

    public void setMapping_endDate(String mapping_endDate) {
        this.mapping_endDate = mapping_endDate;
    }

    public String getMapping_acceptanceDepartment() {
        return mapping_acceptanceDepartment;
    }

    public void setMapping_acceptanceDepartment(String mapping_acceptanceDepartment) {
        this.mapping_acceptanceDepartment = mapping_acceptanceDepartment;
    }

    public Double getMapping_projectAmount() {
        return mapping_projectAmount;
    }

    public void setMapping_projectAmount(Double mapping_projectAmount) {
        this.mapping_projectAmount = mapping_projectAmount;
    }

    public Integer getMapping_schedule() {
        return mapping_schedule;
    }

    public void setMapping_schedule(Integer mapping_schedule) {
        this.mapping_schedule = mapping_schedule;
    }

    public Long getResult_id() {
        return result_id;
    }

    public void setResult_id(Long result_id) {
        this.result_id = result_id;
    }

    public String getResult_plotting_scale() {
        return result_plotting_scale;
    }

    public void setResult_plotting_scale(String result_plotting_scale) {
        this.result_plotting_scale = result_plotting_scale;
    }

    public String getResult_medium_type() {
        return result_medium_type;
    }

    public void setResult_medium_type(String result_medium_type) {
        this.result_medium_type = result_medium_type;
    }

    public Date getResult_date() {
        return result_date;
    }

    public void setResult_date(Date result_date) {
        this.result_date = result_date;
    }

    public String getResult_name() {
        return result_name;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    public Date getResult_create_date() {
        return result_create_date;
    }

    public void setResult_create_date(Date result_create_date) {
        this.result_create_date = result_create_date;
    }

    @Override
    public String toString() {
        return "MappingProjectAndResultCommitVo{" +
                "mapping_id=" + mapping_id +
                ", mapping_projectType='" + mapping_projectType + '\'' +
                ", mapping_projectName='" + mapping_projectName + '\'' +
                ", mapping_projectLeader='" + mapping_projectLeader + '\'' +
                ", mapping_startDate='" + mapping_startDate + '\'' +
                ", mapping_endDate='" + mapping_endDate + '\'' +
                ", mapping_acceptanceDepartment='" + mapping_acceptanceDepartment + '\'' +
                ", mapping_projectAmount=" + mapping_projectAmount +
                ", mapping_schedule=" + mapping_schedule +
                ", result_id=" + result_id +
                ", result_plotting_scale='" + result_plotting_scale + '\'' +
                ", result_medium_type='" + result_medium_type + '\'' +
                ", result_date=" + result_date +
                ", result_name='" + result_name + '\'' +
                ", result_create_date=" + result_create_date +
                '}';
    }
}
