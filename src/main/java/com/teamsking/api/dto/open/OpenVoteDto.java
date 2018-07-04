package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;


@Data
public class OpenVoteDto extends Dto {

    private Integer id;
    private Integer openId;
    private Integer courseId;
    private String title;
    private String description;
    private Integer type;
    private Date startTime;
    private Date endTime;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;
    private String content;

}
