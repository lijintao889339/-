package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import com.teamsking.api.dto.category.AddCategoryNameDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AddOpenDto extends Dto {

    private Integer id;

    private Integer[] userId;

    private Integer[] teacherId;

    //private Integer courseId;

    private Integer firstCategoryId;

    private Integer categoryId;

    private String openName;

    //private Integer[] teacherId;
    private Date beginTime;

    private Date endTime;

    private String openCover;

    private Integer visibleRange;

    private String openIntroduce;

    private String keyWord;

    private Integer openFree;

    //退课权限
    private Integer dropCourse;
    //教学安排
    private Integer openArrange;
    //一句话介绍
    private Integer simpleInfo;

    //开课范围(10：教师导入 20 本校学生 30 全部学员)
    private Integer openMode;

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

    //班课成绩设置
    private OpenSetDto openSetDto;

}
