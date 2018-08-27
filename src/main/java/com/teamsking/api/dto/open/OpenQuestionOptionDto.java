package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class OpenQuestionOptionDto extends Dto {

    private Integer id;

    private Integer questionId;

    private String option;

    //问卷人数
    private Integer questionNums;

    //问卷人数百分比
    private String questionRatio;

    private String optionCover;

}
