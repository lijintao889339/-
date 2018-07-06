package com.teamsking.api.dto.study;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;


@Data
public class StudyQuestionnaireDto extends Dto {

    private Integer id;
    private Integer openId;
    private Integer questionnaireId;
    private Integer subjectId;
    private Integer userId;
    private String answer;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
