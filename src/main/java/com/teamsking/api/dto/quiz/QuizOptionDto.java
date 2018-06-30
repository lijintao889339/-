package com.teamsking.api.dto.quiz;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class QuizOptionDto extends Dto {

    private Integer id;

    private Integer quizId;

    private Integer openId;

    private Integer courseId;

    private Integer optionTitle;

    private Integer correctFlag;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}


