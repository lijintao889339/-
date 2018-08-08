package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class OpenEvaluateInfoDto extends Dto {

    private String userName;

    private String avatar;

    private String openName;

    private Long evaluate;

    private List<String> teacherName;

    private String evaluateContent;

    private String categoryName;

}
