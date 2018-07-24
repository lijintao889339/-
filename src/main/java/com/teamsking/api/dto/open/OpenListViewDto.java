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

    private String openCover;

    private Integer schoolId;

    //所属学校（school实体类）
    private String schoolName;

    private String openName;

    //班次售卖
    private String openSell;

    //学生数量
    private Integer studentNum;

}
