package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.SchoolManager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SchoolManagerDtoMapper {

    SchoolManagerDtoMapper INSTANCE = Mappers.getMapper(SchoolManagerDtoMapper.class);

    List<SchoolManagerDto> entityListToDtoList(List<SchoolManager> entityList);

    SchoolManagerDto entityToDto(SchoolManager schoolManager);

    SchoolManager dtoToEntity(SchoolManagerDto schoolManagerDto);

}
