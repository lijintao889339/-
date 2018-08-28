package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenAssignment;
import java.util.List;

import com.teamsking.domain.entity.sys.UserStudentAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface OpenAssignmentDtoMapper {

    OpenAssignmentDtoMapper INSTANCE = Mappers.getMapper(OpenAssignmentDtoMapper.class);

    List<OpenAssignmentDto> entityListToDtoList(List<UserStudentAssignment> entityList);

    OpenAssignmentDto entityToDto(OpenAssignment openAssignment);

    OpenAssignment dtoToEntity(OpenAssignmentDto openAssignmentDto);

    OpenAssignment InterDtoEntity(AddOpenAssignmentDto addOpenAssignmentDto);

    List<OpenAssignmentNameDto> entityListToNameDtoList(List<OpenAssignment> openAssignmentList);

}
