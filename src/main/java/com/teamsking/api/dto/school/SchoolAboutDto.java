package com.teamsking.api.dto.school;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class SchoolAboutDto extends Dto {

    private Integer id;

    private Integer schoolId;

    private String title;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String context;

}
