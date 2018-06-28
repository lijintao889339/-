package com.teamsking.api.dto;

import com.teamsking.domain.entity.CoursePage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
