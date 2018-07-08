package com.teamsking.api.dto.open;

import com.teamsking.domain.entity.open.OpenSection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface OpenSectionDtoMapper {

    OpenSectionDtoMapper INSTANCE = Mappers.getMapper(OpenSectionDtoMapper.class);

    List<OpenSectionDto> entityListToDtoList(List<OpenSection> entityList);

    OpenSectionDto EntityToDto(OpenSection openSection);

    OpenSection dtoToEntity(OpenSectionDto openSectionDto);

}
