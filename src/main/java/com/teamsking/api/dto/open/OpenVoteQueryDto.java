package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class OpenVoteQueryDto extends Dto {

    private Integer id;

    private Integer activityId;

    private String title;

    private Date startTime;

    private Integer activityType;

    private Integer allUserNums;

    private Integer commitUserNums;

}
