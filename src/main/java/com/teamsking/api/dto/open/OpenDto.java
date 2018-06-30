package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OpenDto extends Dto {

    private Integer id;
    private Integer schoolId;
    private Integer courseId;
    private String openName;
    private Integer openMode;
    private Integer studyMode;
    private String courseIntroduction;
    private BigDecimal courseCredit;
    private Integer studyHour;
    private Integer scoreDay;
    private Integer previewVideo;
    private Integer dropCourse;
    private Date beginTime;
    private Date endTime;
    private Integer courseStatus;
    private Integer publishFlag;
    private Date publishTime;
    private Integer publishId;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;
    private Integer difficultyStatus;
    private Integer courseType;

}
