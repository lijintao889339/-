package com.teamsking.api.dto.course;

import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class CourseEditDto {

    private String courseName;

    private String simpleInfo;

    private List<String> teacherName;

    private String courseIntroduction;

}
