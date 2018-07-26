package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class CourseSchoolDto extends Dto {

    private Integer id;

    private String courseName;

    private Integer schoolId;

    private String schoolName;

}
