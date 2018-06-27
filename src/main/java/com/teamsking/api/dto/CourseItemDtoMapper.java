package com.teamsking.api.dto;

import com.teamsking.domain.entity.CourseItem;
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
