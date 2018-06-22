package com.teamsking.api.dto;

import com.teamsking.domain.entity.SysMenu;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ynfeng
 */
@Mapper
public interface SysMenuDtoMapper {
    SysMenuDtoMapper INSTANCE = Mappers.getMapper(SysMenuDtoMapper.class);

    List<SysMenuDto> entityListToDtoList(List<SysMenu> entityList);

    SysMenuDto entityToDto(SysMenu sysMenu);

    SysMenu dtoToEntity(SysMenuDto sysMenuDto);
}
