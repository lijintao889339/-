package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenEvaluate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenEvaluateDtoMapper {

    OpenEvaluateDtoMapper INSTANCE = Mappers.getMapper(OpenEvaluateDtoMapper.class);

    List<OpenEvaluateDto> entityListToDtoList(List<OpenEvaluate> entityList);

    OpenEvaluateDto entityToDto(OpenEvaluate openEvaluate);

    OpenEvaluate dtoToEntity(OpenEvaluateDto openEvaluateDto);

    OpenEvaluateInfoDto entityToInfoDto(OpenEvaluate openEvaluate);

}
