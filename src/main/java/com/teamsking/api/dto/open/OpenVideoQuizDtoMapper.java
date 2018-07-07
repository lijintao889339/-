package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenVideoQuiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenVideoQuizDtoMapper {

    OpenVideoQuizDtoMapper INSTANCE = Mappers.getMapper(OpenVideoQuizDtoMapper.class);

    List<OpenVideoQuizDto> entityListToDtoList(List<OpenVideoQuiz> entityList);

    OpenVideoQuizDto entityToDto(OpenVideoQuiz openVideoQuiz);

    OpenVideoQuiz dtoToEntity(OpenVideoQuizDto openVideoQuizDto);


}
