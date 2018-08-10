package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface CourseItemDtoMapper {

    CourseItemDtoMapper INSTANCE = Mappers.getMapper(CourseItemDtoMapper.class);

    List<CourseItemDto> entityListToDtoList(List<CourseItem> entityList);

    @Mapping(source = "itemName", target = "name")
    CourseItemDto entityToDto(CourseItem courseItem);

    CourseItem dtoToEntity(CourseItemDto courseItemDto);

}
