package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.Course;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseDtoMapper {

    CourseDtoMapper INSTANCE = Mappers.getMapper(CourseDtoMapper.class);

    List<CourseDto> entityListToDtoList(List<Course> entityList);

    CourseDto entityToDto(Course course);

    Course dtoToEntity(CourseDto courseDto);

    CourseListViewDto entityToListViewDto(Course course);
}
