package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenVoteOptionDto extends Dto {

    private Integer id;

    private Integer voteId;

    private String voteOption;

    //投票人数
    private Integer voteNums;

    //投票人数百分比
    private String voteRatio;

}
