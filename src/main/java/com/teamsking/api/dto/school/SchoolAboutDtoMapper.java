package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.SchoolAbout;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
