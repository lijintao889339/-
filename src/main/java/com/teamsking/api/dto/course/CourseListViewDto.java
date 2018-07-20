package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 课程列表视图用DTO
 *
 * @author ynfeng
 */
@Data
public class CourseListViewDto extends Dto {
    private Integer id;

    private String courseName;

    private String courseCover;

    private Integer courseFree;

    private String courseStatus;

    private String teacherName;

    private int openNum;

    private String qcCode;
}
