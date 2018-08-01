package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AddOpenDto extends Dto {

    private Integer id;

    private Integer roleId;

    private Integer courseId;

    private Integer categoryId;

    private String openName;

    //private Integer[] teacherId;
    private Date beginTime;

    private Date endTime;

    private String openCover;

    private Integer visibleRange;

    private String openIntroduce;

    private Integer keyWord;

    private String openFree;

    //模式
    private Integer studyMode;

    //学分
    private BigDecimal courseCredit;

    //总课时
    private Integer studyHour;

    //后几天公布成绩
    private Integer scoreDay;

    //试学章节(前几个视频)
    private Integer previewVideo;

}
