package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import com.teamsking.domain.entity.open.OpenSet;
import lombok.Data;

import java.util.List;


@Data
public class QueryOpenSetDto extends Dto {

    private Integer id;
    private String userName;
    private Integer onlinePercent;
    private Integer videoPercent;
    private Integer quizPercent;
    private Integer assignmentPercent;
    private Integer topicPercent;
    private Integer examPercent;
    private Integer offlinePercent;
    private String groupName;
    private String status;

    private OpenSet openSet;

}
