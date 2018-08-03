package com.teamsking.api.dto.course;

import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseSectionDto {

    private Integer id;

    private Integer courseId;

    private Integer chapterId;

    private String title;
}
