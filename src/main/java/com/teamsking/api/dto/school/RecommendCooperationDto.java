package com.teamsking.api.dto.school;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class RecommendCooperationDto extends Dto {

    private Integer id;

    private Integer schoolId;

    private Integer openId;

    private String info;

    private String link;

    private Integer displayOrder;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}