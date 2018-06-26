package com.teamsking.api.dto;

import lombok.Data;

@Data
public class SysRoleMenuDto extends Dto{

    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Integer permissionType;

}
