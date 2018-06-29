package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class CoursePageDto extends Dto {

    private Integer id;

    private Integer courseId;

    private String title;

    private Integer pageType;

    private String pageUrl;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String pageContent;


}
