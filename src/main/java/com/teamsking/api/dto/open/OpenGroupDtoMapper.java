package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenGroup;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenGroupDtoMapper {

    OpenGroupDtoMapper INSTANCE = Mappers.getMapper(OpenGroupDtoMapper.class);

    List<OpenGroupDto> entityListToDtoList(List<OpenGroup> entityList);

    OpenGroupDto entityToDto(OpenGroup openGroup);

    OpenGroup dtoToEntity(OpenGroupDto openGroupDto);


}
