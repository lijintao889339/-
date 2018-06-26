package com.teamsking.api.dto;

import com.teamsking.domain.entity.SysRoleMenu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleMenuDtoMapper {

    SysRoleMenuDtoMapper INSTANCE = Mappers.getMapper(SysRoleMenuDtoMapper.class);

    List<SysRoleMenuDto> entityListToDtoList(List<SysRoleMenu> entityList);

    SysRoleMenuDto entityToDto(SysRoleMenu sysRoleMenu);

    SysRoleMenu dtoToEntity(SysRoleMenuDto sysRoleMenuDto);

}
