package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;


@Data
public class CourseEvaluateDto extends Dto {

    private Integer id;

    private Integer categoryId;

    private Integer userId;

    private String studentNo;

    private Integer courseId;

    private String userName;

    private String courseName;

    private Double evaluate;

    private List<String> teacherName;

    private Boolean isShow;

    private String categoryName;

    private String avatar;

    private String evaluateContent;


}
