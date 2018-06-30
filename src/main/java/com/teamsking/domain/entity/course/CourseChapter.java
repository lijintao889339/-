package com.teamsking.domain.entity.course;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`course_chapter`")
public class CourseChapter {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.course_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.chapter_name
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private String chapterName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.display_order
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer displayOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer deleteStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_chapter.chapter_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer chapterStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.id
     *
     * @return the value of course_chapter.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.id
     *
     * @param id the value for course_chapter.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.course_id
     *
     * @return the value of course_chapter.course_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.course_id
     *
     * @param courseId the value for course_chapter.course_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.chapter_name
     *
     * @return the value of course_chapter.chapter_name
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public String getChapterName() {
        return chapterName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.chapter_name
     *
     * @param chapterName the value for course_chapter.chapter_name
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.display_order
     *
     * @return the value of course_chapter.display_order
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.display_order
     *
     * @param displayOrder the value for course_chapter.display_order
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.create_id
     *
     * @return the value of course_chapter.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.create_id
     *
     * @param createId the value for course_chapter.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.create_time
     *
     * @return the value of course_chapter.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.create_time
     *
     * @param createTime the value for course_chapter.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.update_id
     *
     * @return the value of course_chapter.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.update_id
     *
     * @param updateId the value for course_chapter.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.update_time
     *
     * @return the value of course_chapter.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.update_time
     *
     * @param updateTime the value for course_chapter.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.delete_status
     *
     * @return the value of course_chapter.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.delete_status
     *
     * @param deleteStatus the value for course_chapter.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_chapter.chapter_status
     *
     * @return the value of course_chapter.chapter_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getChapterStatus() {
        return chapterStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_chapter.chapter_status
     *
     * @param chapterStatus the value for course_chapter.chapter_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setChapterStatus(Integer chapterStatus) {
        this.chapterStatus = chapterStatus;
    }
}