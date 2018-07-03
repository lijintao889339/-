package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenExplain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenExplainDtoMapper {

    OpenExplainDtoMapper INSTANCE = Mappers.getMapper(OpenExplainDtoMapper.class);

    List<OpenExplainDto> entityListToDtoList(List<OpenExplain> entityList);

    OpenExplainDto entityToDto(OpenExplain openExplain);

    OpenExplain dtoToEntity(OpenExplainDto openExplainDto);

}
