package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.math.BigDecimal;

/**
*@author linhao
*/
@Data
public class AddOpenCourseDto extends Dto {

    private Integer id;

    private Integer[] userId;

    private Integer[] teacherId;

    private Integer categoryId;

    private Integer firstCategoryId;

    private String openName;

    private String openCover;

    private String openIntroduce;

    private String keyWord;

    private Integer openFree;

    //学习模式(10：开放学习 20传统学习 30顺序学习)
    private Integer studyMode;

    //学分
    private BigDecimal courseCredit;

    //总课时
    private Integer studyHour;

    //后几天公布成绩
    private Integer scoreDay;

    //试学章节(前几个视频)
    private Integer previewVideo;

    //开课范围(10：教师导入 20 本校学生 30 全部学员)
    private Integer openMode;

    //退课权限：1 允许退课 2 不允许
    private Integer dropCourse;

    //教学安排
    private String openArrange;

    //课程简介
    private String simpleInfo;

    //班课成绩设置
    private OpenSetDto openSetDto;

}
