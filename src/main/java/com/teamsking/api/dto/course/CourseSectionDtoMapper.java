package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseSection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface CourseSectionDtoMapper {

    CourseSectionDtoMapper INSTANCE = Mappers.getMapper(CourseSectionDtoMapper.class);

    List<CourseSectionDto> entityListToDtoList(List<CourseSection> entityList);

    CourseSectionDto entityToDto(CourseSection courseSection);

    CourseSection dtoToEntity(CourseSectionDto courseSectionDto);

}
