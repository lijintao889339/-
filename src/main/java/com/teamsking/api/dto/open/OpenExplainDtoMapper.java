package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenExplain;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenExplainDtoMapper {

    OpenExplainDtoMapper INSTANCE = Mappers.getMapper(OpenExplainDtoMapper.class);

    List<OpenExplainDto> entityListToDtoList(List<OpenExplain> entityList);

    OpenExplainDto entityToDto(OpenExplain openExplain);

    OpenExplain dtoToEntity(OpenExplainDto openExplainDto);

}
