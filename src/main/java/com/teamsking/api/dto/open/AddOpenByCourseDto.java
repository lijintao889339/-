package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import com.teamsking.api.dto.course.CourseTeacherNameDto;
import lombok.Data;
import org.mapstruct.Mapping;

import java.util.List;

/**
*@author linhao
*/
@Data
public class AddOpenByCourseDto extends Dto {

    //private Integer courseId;

    private String openName;

    private String keyWord;

    private Integer openFree;

    private List<CourseTeacherNameDto> teacherNameDtoList;

    //班课介绍(相当于课程介绍)
    private String openIntroduce;

    //简介(相当于课程简介)
    private String courseIntroduction;

    private Integer courseStatus;

}
