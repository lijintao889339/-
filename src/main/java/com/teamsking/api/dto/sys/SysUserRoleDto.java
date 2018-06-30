package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class SysUserRoleDto extends Dto {

    private Integer id;

    private Integer userId;

    private Integer roleId;

}
