package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class OpenQuestionDto extends Dto {

    private Integer id;

    private Integer courseId;

    private Integer openId;

    private String title;

    private String remark;

    private Date startTime;

    private Date endTime;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;


}
