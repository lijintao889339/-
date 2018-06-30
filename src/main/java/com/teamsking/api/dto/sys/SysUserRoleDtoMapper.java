package com.teamsking.api.dto.sys;

import com.teamsking.domain.entity.sys.SysUserRole;
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
