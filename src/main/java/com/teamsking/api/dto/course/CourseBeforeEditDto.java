package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class CourseBeforeEditDto extends Dto {

    private String courseName;

    private String courseCover;

    private String keyWord;

    private Integer courseFree;

    private Integer visibleRange;

    private String courseIntroduction;

    private Integer categoryId;

    private List<String> teacherNameList;

}
