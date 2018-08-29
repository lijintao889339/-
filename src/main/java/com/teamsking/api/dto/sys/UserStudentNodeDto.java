package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class UserStudentNodeDto extends Dto {

    private Integer userStudentId;

    private String userName;

    private String groupName;

    private Integer watchSeconds;

    private String watchRate;

}
