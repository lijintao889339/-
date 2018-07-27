package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.course.CourseTeacher;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseTeacherDtoMapper {

    CourseTeacherDtoMapper INSTANCE = Mappers.getMapper(CourseTeacherDtoMapper.class);

    List<CourseTeacherDto> entityListToDtoList(List<CourseTeacher> entityList);

    CourseTeacherDto entityToDto(CourseTeacher courseTeacher);

    CourseTeacher dtoToEntity(CourseTeacherDto courseTeacherDto);
}
