package com.teamsking.api.dto.sys;


import com.teamsking.domain.entity.sys.SysRole;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SysRoleDtoMapper {

    SysRoleDtoMapper INSTANCE = Mappers.getMapper(SysRoleDtoMapper.class);

    List<SysRoleDto> entityListToDtoList(List<SysRole> entityList);

    SysRoleDto entityToDto(SysRole sysRole);

    SysRole dtoToEntity(SysRoleDto sysRoleDto);
}
