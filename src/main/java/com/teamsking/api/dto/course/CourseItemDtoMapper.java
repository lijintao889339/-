package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface CourseItemDtoMapper {

    CourseItemDtoMapper INSTANCE = Mappers.getMapper(CourseItemDtoMapper.class);

    List<CourseItemDto> entityListToDtoList(List<CourseItem> entityList);

    CourseItemDto entityToDto(CourseItem courseItem);

    CourseItem dtoToEntity(CourseItemDto courseItemDto);

}
