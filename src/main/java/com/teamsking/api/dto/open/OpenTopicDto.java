package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenTopicDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer chapterId;

    private Integer sectionId;

    private Integer contentId;

    //讨论标题
    private String title;

    //讨论内容
    private String content;

    private Date startTime;

    private Date endTime;

    //成绩公布时间
    private Date scorePublishTime;


}
