package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class OpenQuestionQueryDto extends Dto {

    private Integer id;

    private String title;

    private Date startTime;

    private Integer allUserNums;

    private Integer commitUserNums;

    private Integer publishUserId;

    //发布人
    private String userName;

}
