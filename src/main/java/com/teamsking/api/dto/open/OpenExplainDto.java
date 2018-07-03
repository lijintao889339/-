package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenExplainDto extends Dto {


    private Integer id;
    private Integer courseId;
    private Integer openId;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;
    private String courseIntroduction;
    private String teachPlan;
    private String evaluationMode;
    private String studyTarget;
    private String studyRequirement;
    private String teachingMaterial;


}
