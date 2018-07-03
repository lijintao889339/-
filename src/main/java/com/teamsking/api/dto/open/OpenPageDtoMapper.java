package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenPageDtoMapper {

    OpenPageDtoMapper INSTANCE = Mappers.getMapper(OpenPageDtoMapper.class);

    List<OpenPageDto> entityListToDtoList(List<OpenPage> entityList);

    OpenPageDto entityToDto(OpenPage openPage);

    OpenPage dtoToEntity(OpenPageDto openPageDto);

}
