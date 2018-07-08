package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.RecommendOpen;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
