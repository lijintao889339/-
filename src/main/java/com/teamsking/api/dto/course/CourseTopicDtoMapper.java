package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseTopic;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

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
