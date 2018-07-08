package com.teamsking.api.dto.study;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class StudyVoteDto extends Dto {

    private Integer id;
    private Integer openId;
    private Integer voteId;
    private Integer userId;
    private Integer optionId;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
