package com.teamsking.api.dto.quiz;

import com.teamsking.domain.entity.quiz.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface QuizDtoMapper {

    QuizDtoMapper INSTANCE = Mappers.getMapper(QuizDtoMapper.class);

    List<QuizDto> entityListToDtoList(List<Quiz> entityList);

    QuizDto entityToDto(Quiz quiz);

    Quiz dtoToEntity(QuizDto quizDto);

}
