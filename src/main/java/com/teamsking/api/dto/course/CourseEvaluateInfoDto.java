package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class CourseEvaluateInfoDto extends Dto {

    private String userName;

    private String avatar;

    private String courseName;

    private String evaluate;

    private List<String> teacherName;

    private String evaluateContent;

    private String categoryName;

}
