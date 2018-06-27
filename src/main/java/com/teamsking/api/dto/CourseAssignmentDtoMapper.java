package com.teamsking.api.dto;

import com.teamsking.domain.entity.CourseAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseAssignmentDtoMapper {

    CourseAssignmentDtoMapper INSTANCE = Mappers.getMapper(CourseAssignmentDtoMapper.class);

    List<CourseAssignmentDto> entityListToDtoList(List<CourseAssignment> entityList);

    CourseAssignmentDto entityToDto(CourseAssignment courseAssignment);

    CourseAssignment dtoToEntity(CourseAssignmentDto courseAssignmentDto);

}
