package com.iss.ua.lark.system.domain;

import java.time.LocalDateTime;

/**
 * @author JunoSong
 * @version V1.0
 * @Package com.iss.ua.larksystem.domain
 * @date 2023/3/1 13:53
 */
public class Activity {

    private Long id;

    private String activityName;

    private String listAdminId;

    private String listAdminName;

    private String listCreatorId;

    private String listCreatorName;

    private LocalDateTime activityStartTime;

    private LocalDateTime activityEndTime;

    private LocalDateTime registrationStartTime;

    private LocalDateTime registrationEndTime;

    private Long provinceId;

    private String provinceName;

    private Long cityId;

    private String cityName;

    private String addr;

    private String pic;

    private Long picId;

    private String activityDetail;

    private String activityRedirectUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer deleted;

    private Integer published;

    public Activity() {
    }

    public Activity(Long id, String activityName, String listAdminId, String listAdminName, String listCreatorId, String listCreatorName, LocalDateTime activityStartTime, LocalDateTime activityEndTime, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime, Long provinceId, String provinceName, Long cityId, String cityName, String addr, String pic, Long picId, String activityDetail, String activityRedirectUrl, LocalDateTime createTime, LocalDateTime updateTime, Integer deleted, Integer published) {
        this.id = id;
        this.activityName = activityName;
        this.listAdminId = listAdminId;
        this.listAdminName = listAdminName;
        this.listCreatorId = listCreatorId;
        this.listCreatorName = listCreatorName;
        this.activityStartTime = activityStartTime;
        this.activityEndTime = activityEndTime;
        this.registrationStartTime = registrationStartTime;
        this.registrationEndTime = registrationEndTime;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.addr = addr;
        this.pic = pic;
        this.picId = picId;
        this.activityDetail = activityDetail;
        this.activityRedirectUrl = activityRedirectUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.published = published;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getListAdminId() {
        return listAdminId;
    }

    public void setListAdminId(String listAdminId) {
        this.listAdminId = listAdminId;
    }

    public String getListAdminName() {
        return listAdminName;
    }

    public void setListAdminName(String listAdminName) {
        this.listAdminName = listAdminName;
    }

    public String getListCreatorId() {
        return listCreatorId;
    }

    public void setListCreatorId(String listCreatorId) {
        this.listCreatorId = listCreatorId;
    }

    public String getListCreatorName() {
        return listCreatorName;
    }

    public void setListCreatorName(String listCreatorName) {
        this.listCreatorName = listCreatorName;
    }

    public LocalDateTime getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(LocalDateTime activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public LocalDateTime getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(LocalDateTime activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public LocalDateTime getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(LocalDateTime registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public LocalDateTime getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(LocalDateTime registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }

    public String getActivityRedirectUrl() {
        return activityRedirectUrl;
    }

    public void setActivityRedirectUrl(String activityRedirectUrl) {
        this.activityRedirectUrl = activityRedirectUrl;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }
}
