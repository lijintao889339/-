package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenAssistant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenAssistantDtoMapper {

    OpenAssistantDtoMapper INSTANCE = Mappers.getMapper(OpenAssistantDtoMapper.class);

    List<OpenAssistantDto> entityListToDtoList(List<OpenAssistant> entityList);

    OpenAssistantDto entityToDto(OpenAssistant openAssistant);

    OpenAssistant dtoToEntity(OpenAssistantDto openAssistantDto);



}
