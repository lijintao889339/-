package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class SysRoleDto extends Dto {

    private Integer id;

    private String roleName;

    private String describe;


}
