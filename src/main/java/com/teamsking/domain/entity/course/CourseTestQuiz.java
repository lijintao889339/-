package com.teamsking.domain.entity.course;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`course_test_quiz`")
public class CourseTestQuiz {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.course_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.test_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer testId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.section_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer sectionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.school_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer schoolId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.title
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.quiz_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer quizId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_test_quiz.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.id
     *
     * @return the value of course_test_quiz.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.id
     *
     * @param id the value for course_test_quiz.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.course_id
     *
     * @return the value of course_test_quiz.course_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.course_id
     *
     * @param courseId the value for course_test_quiz.course_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.test_id
     *
     * @return the value of course_test_quiz.test_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getTestId() {
        return testId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.test_id
     *
     * @param testId the value for course_test_quiz.test_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.section_id
     *
     * @return the value of course_test_quiz.section_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.section_id
     *
     * @param sectionId the value for course_test_quiz.section_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.school_id
     *
     * @return the value of course_test_quiz.school_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.school_id
     *
     * @param schoolId the value for course_test_quiz.school_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.title
     *
     * @return the value of course_test_quiz.title
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.title
     *
     * @param title the value for course_test_quiz.title
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.quiz_id
     *
     * @return the value of course_test_quiz.quiz_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getQuizId() {
        return quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.quiz_id
     *
     * @param quizId the value for course_test_quiz.quiz_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.create_id
     *
     * @return the value of course_test_quiz.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.create_id
     *
     * @param createId the value for course_test_quiz.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.create_time
     *
     * @return the value of course_test_quiz.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.create_time
     *
     * @param createTime the value for course_test_quiz.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.update_id
     *
     * @return the value of course_test_quiz.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.update_id
     *
     * @param updateId the value for course_test_quiz.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.update_time
     *
     * @return the value of course_test_quiz.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.update_time
     *
     * @param updateTime the value for course_test_quiz.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_test_quiz.delete_status
     *
     * @return the value of course_test_quiz.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_test_quiz.delete_status
     *
     * @param deleteStatus the value for course_test_quiz.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}