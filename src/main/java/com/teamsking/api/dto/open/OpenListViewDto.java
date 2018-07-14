package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
 * 班次列表视图用DTO
*@author linhao
*/
@Data
public class OpenListViewDto extends Dto {

    private Integer id;

    private Integer openMode;

    private Date beginTime;

    private Date endTime;

    private Integer publishFlag;

    //课程封面地址（node实体类）
    private String coverPath;

    //所属学校（school实体类）
    private String schoolName;

    //所属课程（course实体类）
    private String courseName;

    //课程售卖（course实体类）
    private String courseSell;

    //学生数量（openGroup实体类）
    private Integer userCount;

}
