package com.teamsking.domain.entity.school;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`school_about`")
public class SchoolAbout {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.school_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer schoolId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.title
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private Integer deleteStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column school_about.context
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    private String context;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.id
     *
     * @return the value of school_about.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.id
     *
     * @param id the value for school_about.id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.school_id
     *
     * @return the value of school_about.school_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.school_id
     *
     * @param schoolId the value for school_about.school_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.title
     *
     * @return the value of school_about.title
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.title
     *
     * @param title the value for school_about.title
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.create_id
     *
     * @return the value of school_about.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.create_id
     *
     * @param createId the value for school_about.create_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.create_time
     *
     * @return the value of school_about.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.create_time
     *
     * @param createTime the value for school_about.create_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.update_id
     *
     * @return the value of school_about.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.update_id
     *
     * @param updateId the value for school_about.update_id
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.update_time
     *
     * @return the value of school_about.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.update_time
     *
     * @param updateTime the value for school_about.update_time
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.delete_status
     *
     * @return the value of school_about.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.delete_status
     *
     * @param deleteStatus the value for school_about.delete_status
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_about.context
     *
     * @return the value of school_about.context
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public String getContext() {
        return context;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_about.context
     *
     * @param context the value for school_about.context
     *
     * @mbg.generated Thu Jul 05 21:18:43 CST 2018
     */
    public void setContext(String context) {
        this.context = context;
    }
}