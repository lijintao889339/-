package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
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

    private boolean courseFree;

    private String courseStatus;

    private String teacherName;

    private int openNum;
}
