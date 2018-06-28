package com.teamsking.api.dto;

import com.teamsking.domain.entity.CourseTest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface CourseTestDtoMapper {

    CourseTestDtoMapper INSTANCE = Mappers.getMapper(CourseTestDtoMapper.class);

    List<CourseTestDto> entityListToDtoList(List<CourseTest> entityList);

    CourseTestDto entityToDto(CourseTest courseTest);

    CourseTest dtoToEntity(CourseTestDto courseTestDto);

}
