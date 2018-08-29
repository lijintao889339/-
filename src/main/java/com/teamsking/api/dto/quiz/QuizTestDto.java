package com.teamsking.api.dto.quiz;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class QuizTestDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer quizId;

    private String optionTitle;

    private Integer correctFlag;

}
