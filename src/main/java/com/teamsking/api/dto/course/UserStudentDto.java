package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class UserStudentDto extends Dto {

    private Integer id;

    private Integer userId;

    private String studentNo;

    private String realName;

    private String mobile;

    //认证状态：1：认证，2：未认证
    private Integer activationStatus;

}
