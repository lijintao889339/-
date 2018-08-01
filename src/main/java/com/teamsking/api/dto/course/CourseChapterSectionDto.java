package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseChapterSectionDto extends Dto {

    private Integer chapterId;

    //章名称
    private String lable;

    private Integer sectionId;

    //节名称
    private String children;

    //是否是章
    private Boolean isFirst;

}
