package com.teamsking.domain.entity.school;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`school_carousel`")
public class SchoolCarousel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.school_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer schoolId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.template_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer templateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.url
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.display_order
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer displayOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_carousel.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.id
     *
     * @return the value of school_carousel.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.id
     *
     * @param id the value for school_carousel.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.school_id
     *
     * @return the value of school_carousel.school_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.school_id
     *
     * @param schoolId the value for school_carousel.school_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.template_id
     *
     * @return the value of school_carousel.template_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.template_id
     *
     * @param templateId the value for school_carousel.template_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.url
     *
     * @return the value of school_carousel.url
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.url
     *
     * @param url the value for school_carousel.url
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.display_order
     *
     * @return the value of school_carousel.display_order
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.display_order
     *
     * @param displayOrder the value for school_carousel.display_order
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.create_id
     *
     * @return the value of school_carousel.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.create_id
     *
     * @param createId the value for school_carousel.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.create_time
     *
     * @return the value of school_carousel.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.create_time
     *
     * @param createTime the value for school_carousel.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.update_id
     *
     * @return the value of school_carousel.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.update_id
     *
     * @param updateId the value for school_carousel.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.update_time
     *
     * @return the value of school_carousel.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.update_time
     *
     * @param updateTime the value for school_carousel.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_carousel.delete_status
     *
     * @return the value of school_carousel.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_carousel.delete_status
     *
     * @param deleteStatus the value for school_carousel.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}