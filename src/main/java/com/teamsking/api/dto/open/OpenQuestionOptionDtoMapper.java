package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenQuestionOption;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenQuestionOptionDtoMapper {

    OpenQuestionOptionDtoMapper INSTANCE = Mappers.getMapper(OpenQuestionOptionDtoMapper.class);

    List<OpenQuestionOptionDto> entityListToDtoList(List<OpenQuestionOption> entityList);

    OpenQuestionOptionDto entityToDto(OpenQuestionOption openQuestionOption);

    OpenQuestionOption dtoToEntity(OpenQuestionOptionDto openQuestionOptionDto);

}
