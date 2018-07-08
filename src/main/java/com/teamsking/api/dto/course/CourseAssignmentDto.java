package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CourseAssignmentDto extends Dto {

    private Integer id;

    private Integer schoolId;

    private Integer courseId;

    private String title;

    private Integer diaplayOrder;

    private String status;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String body;

    private String score;

}
