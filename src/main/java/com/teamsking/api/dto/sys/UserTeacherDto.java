package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class UserTeacherDto extends Dto {

    private Integer id;

    private String userName;

    private Integer userId;

    private String email;

    private Integer schoolId;

    private String schoolName;

    private List<String> groupNameList;

}
