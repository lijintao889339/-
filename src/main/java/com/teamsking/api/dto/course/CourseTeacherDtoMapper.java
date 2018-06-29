package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseTeacherDtoMapper {

    CourseTeacherDtoMapper INSTANCE = Mappers.getMapper(CourseTeacherDtoMapper.class);

    List<CourseTeacherDto> entityListToDtoList(List<CourseTeacher> entityList);

    CourseTeacherDto entityToDto(CourseTeacher courseTeacher);

    CourseTeacher dtoToEntity(CourseTeacherDto courseTeacherDto);

}
