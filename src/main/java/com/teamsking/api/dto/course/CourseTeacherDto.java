package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CourseTeacherDto extends Dto {

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
