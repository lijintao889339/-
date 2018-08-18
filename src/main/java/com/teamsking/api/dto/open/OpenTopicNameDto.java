package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenTopicNameDto extends Dto {

    private Integer id;

    //讨论标题
    private String title;

    private Date endTime;

    private Integer userCount;

    private Integer notUserCount;

    private Integer stopUserCount;



}
