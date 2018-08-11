package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class AddOpenAssignmentDto extends Dto {


    private Integer id;

    private Integer openId;

    private Integer chapterId;

    private Integer sectionId;

    private Integer contentId;

    //作业标题
    private String title;

    //评分描述
    private String scoreDescription;

    //作业描述
    private String body;

    //作业类型
    private Integer workType;

    private Date startTime;

    private Date endTime;

}
