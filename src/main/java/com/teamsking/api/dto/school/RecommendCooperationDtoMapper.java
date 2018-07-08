package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.RecommendCooperation;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
*@author linhao
*/
@Mapper
public interface RecommendCooperationDtoMapper {

    RecommendCooperationDtoMapper INSTANCE = Mappers.getMapper(RecommendCooperationDtoMapper.class);

    List<RecommendCooperationDto> entityListToDtoList(List<RecommendCooperation> entityList);

    RecommendCooperationDto entityToDto(RecommendCooperation recommendCooperation);

    RecommendCooperation dtoToEntity(RecommendCooperationDto recommendCooperationDto);

}
