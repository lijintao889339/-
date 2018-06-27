package com.teamsking.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourseTeacherDto extends Dto{

    private Integer id;

    private String teacherName;

    private Integer courseId;

    private Integer userId;

    private Integer displayOrder;

    private Integer openCourse;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;


}
