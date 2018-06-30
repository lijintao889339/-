package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import javax.persistence.Id;

/**
*@author linhao
*/
@Data
public class CourseTestDto extends Dto {

    private Integer id;

    private Integer courseId;

    private Integer chapterId;

    private Integer sectionId;

    private Integer schoolId;

    private String title;

    private Integer quizCount;

    private Integer isLimit;

    private Integer limitTime;

    private Byte hideResults;

    private Integer submitTimes;

}
