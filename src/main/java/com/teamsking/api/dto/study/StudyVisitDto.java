package com.teamsking.api.dto.study;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class StudyVisitDto extends Dto {

    private Integer id;
    private Integer courseId;
    private Integer openId;
    private Integer unitId;
    private Integer itemId;
    private Integer itemType;
    private Integer contextId;
    private Integer userId;
    private Integer contextType;
    private Integer visitDatetime;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
