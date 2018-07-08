package com.teamsking.api.dto.study;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class StudyNoteDto extends Dto {


    private Integer id;
    private Integer openId;
    private Integer unitId;
    private Integer userId;
    private Boolean publicStatus;
    private Integer voteLogCount;
    private Integer collectCount;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;
    private String noteBody;

}
