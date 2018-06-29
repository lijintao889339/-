package com.teamsking.api.dto.quiz;

import com.teamsking.domain.entity.quiz.QuizOption;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface QuizOptionDtoMapper {

    QuizOptionDtoMapper INSTANCE = Mappers.getMapper(QuizOptionDtoMapper.class);

    List<QuizOptionDto> entityListToDtoList(List<QuizOption> entityList);

    QuizOptionDto entityToDto(QuizOption quizOption);

    QuizOption dtoToEntity(QuizOptionDto quizOptionDto);

}
