package com.teamsking.api.dto.sys;

import com.teamsking.domain.entity.sys.SysRoleMenu;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysRoleMenuDtoMapper {

    SysRoleMenuDtoMapper INSTANCE = Mappers.getMapper(SysRoleMenuDtoMapper.class);

    List<SysRoleMenuDto> entityListToDtoList(List<SysRoleMenu> entityList);

    SysRoleMenuDto entityToDto(SysRoleMenu sysRoleMenu);

    SysRoleMenu dtoToEntity(SysRoleMenuDto sysRoleMenuDto);

}
