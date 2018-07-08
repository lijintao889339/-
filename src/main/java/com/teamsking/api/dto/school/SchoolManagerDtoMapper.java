package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.SchoolManager;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchoolManagerDtoMapper {

    SchoolManagerDtoMapper INSTANCE = Mappers.getMapper(SchoolManagerDtoMapper.class);

    List<SchoolManagerDto> entityListToDtoList(List<SchoolManager> entityList);

    SchoolManagerDto entityToDto(SchoolManager schoolManager);

    SchoolManager dtoToEntity(SchoolManagerDto schoolManagerDto);

}
