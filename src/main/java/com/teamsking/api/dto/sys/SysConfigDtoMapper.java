package com.teamsking.api.dto.sys;

import com.teamsking.domain.entity.sys.SysConfig;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface SysConfigDtoMapper {

    SysConfigDtoMapper INSTANCE = Mappers.getMapper(SysConfigDtoMapper.class);

    List<SysConfigDto> entityListToDtoList(List<SysConfig> entityList);

    SysConfigDto entityToDto(SysConfig sysConfig);

    SysConfig dtoToEntity(SysConfigDto sysConfigDto);

}
