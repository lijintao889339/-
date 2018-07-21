package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;



@Data
public class CourseEvaluateDto extends Dto {

    private Integer id;
    private String userName;
    private String courseName;
    private String evaluate;
    private String teacherName;
    private Integer isShow;
    private String categoryName;


}
