package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class OpenVoteOptionDto extends Dto {

    private Integer id;

    private Integer voteId;

    private Integer openId;

    private String voteOption;

    private String description;

    private Integer displayOrder;

    private Date startTime;

    private Date endTime;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
