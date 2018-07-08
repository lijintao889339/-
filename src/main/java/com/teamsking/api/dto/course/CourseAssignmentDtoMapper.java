package com.teamsking.api.dto.course;

import com.teamsking.domain.entity.course.CourseAssignment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseAssignmentDtoMapper {

    CourseAssignmentDtoMapper INSTANCE = Mappers.getMapper(CourseAssignmentDtoMapper.class);

    List<CourseAssignmentDto> entityListToDtoList(List<CourseAssignment> entityList);

    CourseAssignmentDto entityToDto(CourseAssignment courseAssignment);

    CourseAssignment dtoToEntity(CourseAssignmentDto courseAssignmentDto);

}
