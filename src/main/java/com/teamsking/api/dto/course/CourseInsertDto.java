package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseInsertDto extends Dto {

    private Integer id;

    private String courseName;

    private String courseCover;

    private String keyWord;

    private Integer courseFree;

    private String courseIntroduction;

    private Integer categoryId;

    private Integer firstCategoryId;

    private Integer[] teacherId;

    private Integer[] userId;
}
