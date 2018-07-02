package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface OpenUserDtoMapper {

    OpenUserDtoMapper INSTANCE = Mappers.getMapper(OpenUserDtoMapper.class);

    List<OpenUserDto> entityListToDtoList(List<OpenUser> entityList);

    OpenUserDto entityToDto(OpenUser openUser);

    OpenUser dtoToEntity(OpenUserDto openUserDto);

}
