package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CoursePage;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface CoursePageDtoMapper {

    CoursePageDtoMapper INSTANCE = Mappers.getMapper(CoursePageDtoMapper.class);

    List<CoursePageDto> entityListToDtoList(List<CoursePage> entityList);

    CoursePageDto entityToDto(CoursePage coursePage);

    CoursePage dtoToEntity(CoursePageDto coursePageDto);

}
