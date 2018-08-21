package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenExam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenExamDtoMapper {

    OpenExamDtoMapper INSTANCE = Mappers.getMapper(OpenExamDtoMapper.class);

    List<OpenExamDto> entityListToDtoList(List<OpenExam> entityList);

    OpenExamDto entityToDto(OpenExam openExam);

    OpenExam dtoToEntity(OpenExamDto openExamDto);

    List<OpenExamNameDto> entityListNameDto(List<OpenExam> openExamList);

}
