package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class AddOpenVoteDto extends Dto {

    private Integer id;

    private String title;

    private String description;

    private Integer type;

    private Integer integralReward;

    private Integer endType;

    private Boolean isViewStatistics;

    private Long durationDay;

    private Long durationHour;

    private Long durationMin;

    private Boolean isPublish;

}
