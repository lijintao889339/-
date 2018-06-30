package com.teamsking.api.dto.sys;

import com.teamsking.domain.entity.sys.SysMenu;
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
