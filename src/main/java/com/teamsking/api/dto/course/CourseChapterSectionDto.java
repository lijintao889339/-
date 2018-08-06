package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseChapterSectionDto extends Dto {

    private String label;

    private Boolean isFirstLabel;

    private CourseChapterSecondDto[] children;

}
