package com.teamsking.api.dto.study;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;


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
