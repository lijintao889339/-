package com.teamsking.domain.entity.open;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`open_vote_option`")
public class OpenVoteOption {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    @Id
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.vote_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer voteId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.open_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer openId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.vote_option
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private String voteOption;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.description
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.display_order
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer displayOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.start_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.end_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column open_vote_option.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    private Integer deleteStatus;

    private String optionCover;

    public String getOptionCover() {
        return optionCover;
    }

    public void setOptionCover(String optionCover) {
        this.optionCover = optionCover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.id
     *
     * @return the value of open_vote_option.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.id
     *
     * @param id the value for open_vote_option.id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.vote_id
     *
     * @return the value of open_vote_option.vote_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getVoteId() {
        return voteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.vote_id
     *
     * @param voteId the value for open_vote_option.vote_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.open_id
     *
     * @return the value of open_vote_option.open_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.open_id
     *
     * @param openId the value for open_vote_option.open_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.vote_option
     *
     * @return the value of open_vote_option.vote_option
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public String getVoteOption() {
        return voteOption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.vote_option
     *
     * @param voteOption the value for open_vote_option.vote_option
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.description
     *
     * @return the value of open_vote_option.description
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.description
     *
     * @param description the value for open_vote_option.description
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.display_order
     *
     * @return the value of open_vote_option.display_order
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.display_order
     *
     * @param displayOrder the value for open_vote_option.display_order
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.start_time
     *
     * @return the value of open_vote_option.start_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.start_time
     *
     * @param startTime the value for open_vote_option.start_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.end_time
     *
     * @return the value of open_vote_option.end_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.end_time
     *
     * @param endTime the value for open_vote_option.end_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.create_id
     *
     * @return the value of open_vote_option.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.create_id
     *
     * @param createId the value for open_vote_option.create_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.create_time
     *
     * @return the value of open_vote_option.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.create_time
     *
     * @param createTime the value for open_vote_option.create_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.update_id
     *
     * @return the value of open_vote_option.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.update_id
     *
     * @param updateId the value for open_vote_option.update_id
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.update_time
     *
     * @return the value of open_vote_option.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.update_time
     *
     * @param updateTime the value for open_vote_option.update_time
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column open_vote_option.delete_status
     *
     * @return the value of open_vote_option.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column open_vote_option.delete_status
     *
     * @param deleteStatus the value for open_vote_option.delete_status
     *
     * @mbg.generated Tue Jun 26 17:08:40 CST 2018
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
