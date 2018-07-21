package com.teamsking.api.dto.course;


import com.teamsking.domain.entity.course.CourseEvaluate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseEvaluateDtoMapper {

    CourseEvaluateDtoMapper INSTANCE = Mappers.getMapper(CourseEvaluateDtoMapper.class);

    List<CourseEvaluateDto> entityListToDtoList(List<CourseEvaluate> entityList);

    CourseEvaluateDto entityToDto(CourseEvaluate courseEvaluate);

    CourseEvaluate dtoToEntity(CourseEvaluateDto courseEvaluateDto);

    CourseEvaluateInfoDto entityToInfoDto(CourseEvaluate courseEvaluate);

}
