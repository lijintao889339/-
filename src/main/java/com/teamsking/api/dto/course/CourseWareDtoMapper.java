package com.teamsking.api.dto.course;


import com.teamsking.domain.entity.course.CourseWare;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseWareDtoMapper {

    CourseWareDtoMapper INSTANCE = Mappers.getMapper(CourseWareDtoMapper.class);

    List<CourseWareDto> entityListToDtoList(List<CourseWare> entityList);

    CourseWareDto entityToDto(CourseWare courseWare);

    CourseWare dtoToEntity(CourseWareDto courseWareDto);

}
