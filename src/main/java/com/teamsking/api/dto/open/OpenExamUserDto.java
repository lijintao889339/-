package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenExamUserDto extends Dto {

    private Integer id;

    private Integer userStudentId;

    private String userName;

    private String groupName;

    private String status;

    private Date endTime;

    private Integer openId;

    //分数
    private String score;

}
