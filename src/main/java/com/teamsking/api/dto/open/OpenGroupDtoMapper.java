package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenGroupDtoMapper {

    OpenGroupDtoMapper INSTANCE = Mappers.getMapper(OpenGroupDtoMapper.class);

    List<OpenGroupDto> entityListToDtoList(List<OpenGroup> entityList);

    OpenGroupDto entityToDto(OpenGroup openGroup);

    OpenGroup dtoToEntity(OpenGroupDto openGroupDto);


}
