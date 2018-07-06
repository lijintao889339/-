package com.teamsking.api.dto.study;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class StudyWrongQuestionDto extends Dto {

    private Integer id;
    private Integer openId;
    private Integer userId;
    private Integer quizId;
    private Integer errorCount;
    private Integer testId;
    private String answerData;
    private String questionData;

}
