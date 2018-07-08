package com.teamsking.api.dto.quiz;

import com.teamsking.domain.entity.quiz.QuizOption;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
