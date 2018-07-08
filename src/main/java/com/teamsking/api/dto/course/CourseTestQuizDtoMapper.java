package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseTestQuiz;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface CourseTestQuizDtoMapper {

    CourseTestQuizDtoMapper INSTANCE = Mappers.getMapper(CourseTestQuizDtoMapper.class);

    List<CourseTestQuizDto> entityListToDtoList(List<CourseTestQuiz> entityList);

    CourseTestQuizDto entityToDto(CourseTestQuiz courseTestQuiz);

    CourseTestQuiz dtoToEntity(CourseTestQuizDto courseTestQuizDto);

}
