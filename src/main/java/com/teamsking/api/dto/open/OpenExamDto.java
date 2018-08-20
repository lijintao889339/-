package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenExamDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer examType;

    private String title;

    private Date startTime;

    private Date endTime;

    private Integer paperTime;

    private Integer repairCondition;

    private String repairName;

    private Integer examLimit;

    private Double examTypeWeightGuest;

    private Double examTypeWeightMain;

    private Integer scoreDay;

    private Integer cheat;

    private Double joinCondition;

    private Integer quizOrder;

    private Integer optionOrder;


}
