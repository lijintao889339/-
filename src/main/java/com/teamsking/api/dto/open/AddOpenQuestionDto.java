package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class AddOpenQuestionDto extends Dto {

    private Integer id;

    private String title;

    private String remark;

    private Integer type;

    private Integer integralReward;

    private Integer endType;

    private Boolean isViewStatistics;

    private Long durationDay;

    private Long durationHour;

    private Long durationMin;

    private Boolean isPublish;

    private Integer activityId;

    private String activityTitle;

    private String activityContent;

}
