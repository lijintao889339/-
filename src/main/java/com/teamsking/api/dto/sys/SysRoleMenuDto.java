package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class SysRoleMenuDto extends Dto {

    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Integer permissionType;

}
