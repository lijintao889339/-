package com.teamsking.domain.entity.open;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`open_video_quiz`")
public class OpenVideoQuiz {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.open_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer openId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.school_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer schoolId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.video_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer videoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.time_node
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer timeNode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.quiz_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer quizId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_video_quiz.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.id
     *
     * @return the value of open_video_quiz.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.id
     *
     * @param id the value for open_video_quiz.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.open_id
     *
     * @return the value of open_video_quiz.open_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.open_id
     *
     * @param openId the value for open_video_quiz.open_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.school_id
     *
     * @return the value of open_video_quiz.school_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.school_id
     *
     * @param schoolId the value for open_video_quiz.school_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.video_id
     *
     * @return the value of open_video_quiz.video_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.video_id
     *
     * @param videoId the value for open_video_quiz.video_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.time_node
     *
     * @return the value of open_video_quiz.time_node
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getTimeNode() {
        return timeNode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.time_node
     *
     * @param timeNode the value for open_video_quiz.time_node
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setTimeNode(Integer timeNode) {
        this.timeNode = timeNode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.quiz_id
     *
     * @return the value of open_video_quiz.quiz_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getQuizId() {
        return quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.quiz_id
     *
     * @param quizId the value for open_video_quiz.quiz_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.create_id
     *
     * @return the value of open_video_quiz.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.create_id
     *
     * @param createId the value for open_video_quiz.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.create_time
     *
     * @return the value of open_video_quiz.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.create_time
     *
     * @param createTime the value for open_video_quiz.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.update_id
     *
     * @return the value of open_video_quiz.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.update_id
     *
     * @param updateId the value for open_video_quiz.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.update_time
     *
     * @return the value of open_video_quiz.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.update_time
     *
     * @param updateTime the value for open_video_quiz.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_video_quiz.delete_status
     *
     * @return the value of open_video_quiz.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_video_quiz.delete_status
     *
     * @param deleteStatus the value for open_video_quiz.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}