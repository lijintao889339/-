package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.School;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface SchoolDtoMapper {

    SchoolDtoMapper INSTANCE = Mappers.getMapper(SchoolDtoMapper.class);

    List<SchoolDto> entityListToDtoList(List<School> entityList);

    SchoolDto entityToDto(School school);

    School dtoToEntity(SchoolDto schoolDto);

}
