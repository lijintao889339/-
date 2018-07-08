package com.teamsking.api.dto.course;


import com.teamsking.domain.entity.course.CourseWare;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseWareDtoMapper {

    CourseWareDtoMapper INSTANCE = Mappers.getMapper(CourseWareDtoMapper.class);

    List<CourseWareDto> entityListToDtoList(List<CourseWare> entityList);

    CourseWareDto entityToDto(CourseWare courseWare);

    CourseWare dtoToEntity(CourseWareDto courseWareDto);

}
