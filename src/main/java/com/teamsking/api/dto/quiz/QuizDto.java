package com.teamsking.api.dto.quiz;

import com.teamsking.api.dto.Dto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
