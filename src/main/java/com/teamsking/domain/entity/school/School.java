package com.teamsking.domain.entity.school;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`school`")
public class School {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.school_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String schoolName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.short_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String shortName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.en_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String enName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.school_logo
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String schoolLogo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.advertise_pic
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String advertisePic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.school_weburl
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String schoolWeburl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.introduction
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String introduction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.country_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String countryCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.province_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String provinceCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.city_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String cityCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.school_address
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String schoolAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.display_order
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer displayOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.template
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer template;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.id
     *
     * @return the value of school.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.id
     *
     * @param id the value for school.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.school_name
     *
     * @return the value of school.school_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.school_name
     *
     * @param schoolName the value for school.school_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.short_name
     *
     * @return the value of school.short_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.short_name
     *
     * @param shortName the value for school.short_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.en_name
     *
     * @return the value of school.en_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getEnName() {
        return enName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.en_name
     *
     * @param enName the value for school.en_name
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.school_logo
     *
     * @return the value of school.school_logo
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getSchoolLogo() {
        return schoolLogo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.school_logo
     *
     * @param schoolLogo the value for school.school_logo
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.advertise_pic
     *
     * @return the value of school.advertise_pic
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getAdvertisePic() {
        return advertisePic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.advertise_pic
     *
     * @param advertisePic the value for school.advertise_pic
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setAdvertisePic(String advertisePic) {
        this.advertisePic = advertisePic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.school_weburl
     *
     * @return the value of school.school_weburl
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getSchoolWeburl() {
        return schoolWeburl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.school_weburl
     *
     * @param schoolWeburl the value for school.school_weburl
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setSchoolWeburl(String schoolWeburl) {
        this.schoolWeburl = schoolWeburl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.introduction
     *
     * @return the value of school.introduction
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.introduction
     *
     * @param introduction the value for school.introduction
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.country_code
     *
     * @return the value of school.country_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.country_code
     *
     * @param countryCode the value for school.country_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.province_code
     *
     * @return the value of school.province_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.province_code
     *
     * @param provinceCode the value for school.province_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.city_code
     *
     * @return the value of school.city_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.city_code
     *
     * @param cityCode the value for school.city_code
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.school_address
     *
     * @return the value of school.school_address
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getSchoolAddress() {
        return schoolAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.school_address
     *
     * @param schoolAddress the value for school.school_address
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.display_order
     *
     * @return the value of school.display_order
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.display_order
     *
     * @param displayOrder the value for school.display_order
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.template
     *
     * @return the value of school.template
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getTemplate() {
        return template;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.template
     *
     * @param template the value for school.template
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setTemplate(Integer template) {
        this.template = template;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.create_id
     *
     * @return the value of school.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.create_id
     *
     * @param createId the value for school.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.create_time
     *
     * @return the value of school.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.create_time
     *
     * @param createTime the value for school.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.update_id
     *
     * @return the value of school.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.update_id
     *
     * @param updateId the value for school.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.update_time
     *
     * @return the value of school.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.update_time
     *
     * @param updateTime the value for school.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school.delete_status
     *
     * @return the value of school.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school.delete_status
     *
     * @param deleteStatus the value for school.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}