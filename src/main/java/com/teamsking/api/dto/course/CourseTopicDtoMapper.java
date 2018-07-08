package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseTopic;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface CourseTopicDtoMapper {

    CourseTopicDtoMapper INSTANCE = Mappers.getMapper(CourseTopicDtoMapper.class);

    List<CourseTopicDto> entityListToDtoList(List<CourseTopic> entityList);

    CourseTopicDto entityToDto(CourseTopic courseTopic);

    CourseTopic dtoToEntity(CourseTopicDto courseTopicDto);

}
