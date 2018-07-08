package com.teamsking.api.dto.quiz;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class QuizDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer courseId;

    private String quizTitle;

    private Integer quizType;

    private String quizAnalysis;

    private Integer difficultLevel;

    private Integer useType;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String quizDesc;

    private String quizAnswer;


}
