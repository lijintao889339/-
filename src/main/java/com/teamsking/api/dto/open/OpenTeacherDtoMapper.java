package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenTeacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenTeacherDtoMapper {

    OpenTeacherDtoMapper INSTANCE = Mappers.getMapper(OpenTeacherDtoMapper.class);

    List<OpenTeacherDto> entityListToDtoList(List<OpenTeacher> entityList);

    OpenTeacherDto entityToDto(OpenTeacher openTeacher);

    OpenTeacher dtoToEntity(OpenTeacherDto openTeacherDto);

}
