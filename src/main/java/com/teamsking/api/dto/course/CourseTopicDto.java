package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseTopicDto extends Dto {

    private Integer id;

    private Integer courseId;

    private Integer forumId;

    private String title;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String body;

}
