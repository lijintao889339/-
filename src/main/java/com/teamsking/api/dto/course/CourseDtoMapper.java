package com.teamsking.api.dto.course;

import com.teamsking.api.dto.open.AddOpenByCourseDto;
import com.teamsking.domain.entity.course.Course;
import java.util.List;

import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.entity.open.OpenTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseDtoMapper {

    CourseDtoMapper INSTANCE = Mappers.getMapper(CourseDtoMapper.class);

    List<CourseDto> entityListToDtoList(List<Course> entityList);

    CourseDto entityToDto(Course course);

    Course dtoToEntity(CourseDto courseDto);

    CourseListViewDto entityToListViewDto(Course course);

    List<CourseListViewDto> entityToListViewDtoList(List<Course> courses);

    Course insertDtoToEntity(CourseInsertDto courseInsertDto);

    CourseBeforeEditDto entityToBeforeEditDto(Course course);

    CourseSchoolDto entityToCourseSchoolDto(Course course);

    List<CourseSchoolDto> entityToCourseSchoolDtoList(List<Course> courseList);

    @Mapping(source = "simpleInfo", target = "courseIntroduction")
    AddOpenByCourseDto entityToOpenDto(Course course);

}

