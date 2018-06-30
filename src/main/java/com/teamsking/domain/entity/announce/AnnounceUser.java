package com.teamsking.domain.entity.announce;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`announce_user`")
public class AnnounceUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.announce_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer announceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.user_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column announce_user.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.id
     *
     * @return the value of announce_user.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.id
     *
     * @param id the value for announce_user.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.announce_id
     *
     * @return the value of announce_user.announce_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getAnnounceId() {
        return announceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.announce_id
     *
     * @param announceId the value for announce_user.announce_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setAnnounceId(Integer announceId) {
        this.announceId = announceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.user_id
     *
     * @return the value of announce_user.user_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.user_id
     *
     * @param userId the value for announce_user.user_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.create_id
     *
     * @return the value of announce_user.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.create_id
     *
     * @param createId the value for announce_user.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.create_time
     *
     * @return the value of announce_user.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.create_time
     *
     * @param createTime the value for announce_user.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.update_id
     *
     * @return the value of announce_user.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.update_id
     *
     * @param updateId the value for announce_user.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.update_time
     *
     * @return the value of announce_user.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.update_time
     *
     * @param updateTime the value for announce_user.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column announce_user.delete_status
     *
     * @return the value of announce_user.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column announce_user.delete_status
     *
     * @param deleteStatus the value for announce_user.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}