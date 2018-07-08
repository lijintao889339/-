package com.teamsking.api.dto.course;

import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseSectionDto {

    private Integer id;

    private Integer chapterId;

    private Integer courseId;

    private String title;

    private Integer diaplayOrder;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;
}
