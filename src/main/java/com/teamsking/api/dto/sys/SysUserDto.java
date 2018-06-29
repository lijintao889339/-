package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class SysUserDto extends Dto {

    private Integer id;

    private String userName;

    private String realName;

    private String studentNo;

    private String passwd;

    private String mobile;

    private String email;

    private Integer sex;

    private String address;

    private String idCard;

    private Integer education;

    private String college;

    private String department;

    private String schoolClass;

    private String grade;

    private String avatar;

    private Integer activationStatus;

    private Integer deleteStatus;

}
