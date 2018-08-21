package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.List;

import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenQuestionDto extends Dto {

    private Integer id;

    private String title;

    private List<OpenQuestionOptionDto> openQuestionOptionDtos;


}
