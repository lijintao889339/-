package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseTest;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
