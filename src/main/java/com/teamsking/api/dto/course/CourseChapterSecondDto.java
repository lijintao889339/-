package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseChapterSecondDto extends Dto {

    private Integer id;

    private String label;

    private Boolean isShow;

    private Boolean isFirstLabel;

    private Integer parentId;
}
