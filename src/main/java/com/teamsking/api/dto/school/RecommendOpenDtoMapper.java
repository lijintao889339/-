package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.RecommendOpen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface RecommendOpenDtoMapper {

    RecommendOpenDtoMapper INSTANCE = Mappers.getMapper(RecommendOpenDtoMapper.class);

    List<RecommendOpenDto> entityListToDtoList(List<RecommendOpen> entityList);

    RecommendOpenDto entityToDto(RecommendOpen recommendOpen);

    RecommendOpen dtoToEntity(RecommendOpenDto recommendOpenDto);

}
