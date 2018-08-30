package com.teamsking.domain.entity.sys;

import javax.persistence.*;

@Table(name = "`user_student_node`")
public class UserStudentNode {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student_node.id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student_node.user_student_id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    private Integer userStudentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student_node.node_id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    private Integer nodeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student_node.watch_seconds
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    private Integer watchSeconds;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_student_node.delete_status
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    private Integer deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student_node.id
     *
     * @return the value of user_student_node.id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student_node.id
     *
     * @param id the value for user_student_node.id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student_node.user_student_id
     *
     * @return the value of user_student_node.user_student_id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public Integer getUserStudentId() {
        return userStudentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student_node.user_student_id
     *
     * @param userStudentId the value for user_student_node.user_student_id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public void setUserStudentId(Integer userStudentId) {
        this.userStudentId = userStudentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student_node.node_id
     *
     * @return the value of user_student_node.node_id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student_node.node_id
     *
     * @param nodeId the value for user_student_node.node_id
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student_node.watch_seconds
     *
     * @return the value of user_student_node.watch_seconds
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public Integer getWatchSeconds() {
        return watchSeconds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student_node.watch_seconds
     *
     * @param watchSeconds the value for user_student_node.watch_seconds
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public void setWatchSeconds(Integer watchSeconds) {
        this.watchSeconds = watchSeconds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_student_node.delete_status
     *
     * @return the value of user_student_node.delete_status
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_student_node.delete_status
     *
     * @param deleteStatus the value for user_student_node.delete_status
     *
     * @mbg.generated Wed Aug 29 10:12:16 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}