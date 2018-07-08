package com.teamsking.api.dto.sys;

import com.teamsking.domain.entity.sys.SysUserRole;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserRoleDtoMapper {

    SysUserRoleDtoMapper INSTANCE = Mappers.getMapper(SysUserRoleDtoMapper.class);

    List<SysUserRoleDto> entityListToDtoList(List<SysUserRole> entityList);

    SysUserRoleDto entityToDto(SysUserRole sysUserRole);

    SysUserRole dtoToEntity(SysUserRoleDto sysUserRoleDto);

}
