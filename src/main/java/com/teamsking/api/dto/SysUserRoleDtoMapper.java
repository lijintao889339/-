package com.teamsking.api.dto;

import com.teamsking.domain.entity.SysUserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserRoleDtoMapper {

    SysUserRoleDtoMapper INSTANCE = Mappers.getMapper(SysUserRoleDtoMapper.class);

    List<SysUserRoleDto> entityListToDtoList(List<SysUserRole> entityList);

    SysUserRoleDto entityToDto(SysUserRole sysUserRole);

    SysUserRole dtoToEntity(SysUserRoleDto sysUserRoleDto);

}
