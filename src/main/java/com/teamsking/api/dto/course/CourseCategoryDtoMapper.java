package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface CourseCategoryDtoMapper {

    CourseCategoryDtoMapper INSTANCE = Mappers.getMapper(CourseCategoryDtoMapper.class);

    List<CourseCategoryDto> entityListToDtoList(List<CourseCategory> courseCategoryList);

    CourseCategory dtoToEntity(CourseCategoryDto courseCategoryDto);

}
