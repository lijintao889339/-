package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseDtoMapper {

    CourseDtoMapper INSTANCE = Mappers.getMapper(CourseDtoMapper.class);

    List<CourseDto> entityListToDtoList(List<Course> entityList);

    CourseDto entityToDto(Course course);

    Course dtoToEntity(CourseDto courseDto);
}
