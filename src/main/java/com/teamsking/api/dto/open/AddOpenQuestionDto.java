package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class AddOpenQuestionDto extends Dto {

    private Integer id;

    private String title;

    private String questionCover;

    private String remark;

    private Integer type;

    private Integer integralReward;

    private Integer endType;

    private Boolean isViewStatistics;

    private Long durationDay;

    private Long durationHour;

    private Long durationMin;

    private List<OpenQuestionOptionDto> openQuestionOptionDtos;

}
