package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.SchoolAbout;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface SchoolAboutDtoMapper {

    SchoolAboutDtoMapper INSTANCE = Mappers.getMapper(SchoolAboutDtoMapper.class);

    List<SchoolAboutDto> entityListToDtoList(List<SchoolAbout> entityList);

    SchoolAboutDto entityToDto(SchoolAbout schoolAbout);

    SchoolAbout dtoToEntity(SchoolAboutDto schoolAboutDto);

}
