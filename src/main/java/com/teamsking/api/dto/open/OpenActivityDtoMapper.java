package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenActivityDtoMapper {

    OpenActivityDtoMapper INSTANCE = Mappers.getMapper(OpenActivityDtoMapper.class);

    List<OpenActivityDto> entityListToDtoList(List<OpenActivity> entityList);

    OpenActivityDto entityToDto(OpenActivity openActivity);

    OpenActivity dtoToEntity(OpenActivityDto openActivityDto);

}
