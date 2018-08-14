package com.teamsking.domain.entity.sys;

import javax.persistence.*;

@Table(name = "`user_student`")
public class UserStudent {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.id
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.real_name
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String realName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.user_name
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.student_no
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String studentNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.education
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private Integer education;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.college
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String college;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.department
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String department;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.school_class
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String schoolClass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.grade
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String grade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.avatar
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.delete_status
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private Integer deleteStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student.user_id
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    private Integer userId;

    private Integer schoolId;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.id
     *
     * @return the value of user_student.id
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.id
     *
     * @param id the value for user_student.id
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.real_name
     *
     * @return the value of user_student.real_name
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getRealName() {
        return realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.real_name
     *
     * @param realName the value for user_student.real_name
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.user_name
     *
     * @return the value of user_student.user_name
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.user_name
     *
     * @param userName the value for user_student.user_name
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.student_no
     *
     * @return the value of user_student.student_no
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.student_no
     *
     * @param studentNo the value for user_student.student_no
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.education
     *
     * @return the value of user_student.education
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public Integer getEducation() {
        return education;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.education
     *
     * @param education the value for user_student.education
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setEducation(Integer education) {
        this.education = education;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.college
     *
     * @return the value of user_student.college
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getCollege() {
        return college;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.college
     *
     * @param college the value for user_student.college
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.department
     *
     * @return the value of user_student.department
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.department
     *
     * @param department the value for user_student.department
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.school_class
     *
     * @return the value of user_student.school_class
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getSchoolClass() {
        return schoolClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.school_class
     *
     * @param schoolClass the value for user_student.school_class
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.grade
     *
     * @return the value of user_student.grade
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.grade
     *
     * @param grade the value for user_student.grade
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.avatar
     *
     * @return the value of user_student.avatar
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.avatar
     *
     * @param avatar the value for user_student.avatar
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.delete_status
     *
     * @return the value of user_student.delete_status
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.delete_status
     *
     * @param deleteStatus the value for user_student.delete_status
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student.user_id
     *
     * @return the value of user_student.user_id
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student.user_id
     *
     * @param userId the value for user_student.user_id
     *
     * @mbg.generated Mon Aug 13 10:06:46 CST 2018
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}