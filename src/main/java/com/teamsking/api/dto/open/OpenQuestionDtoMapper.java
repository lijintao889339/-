package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface OpenQuestionDtoMapper {

    OpenQuestionDtoMapper INSTANCE = Mappers.getMapper(OpenQuestionDtoMapper.class);

    List<OpenQuestionDto> entityListToDtoList(List<OpenQuestion> entityList);

    OpenQuestionDto EntityToDto(OpenQuestion openQuestion);

    OpenQuestion dtoToEntity(OpenQuestionDto openQuestionDto);

}
