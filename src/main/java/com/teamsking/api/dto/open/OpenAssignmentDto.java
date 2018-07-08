package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenAssignmentDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer courseId;

    private String title;

    private Integer diaplayOrder;

    private String status;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private Date startTime;

    private Date endTime;

    private Integer publishSocreDays;

    private String body;

    private String score;

}
