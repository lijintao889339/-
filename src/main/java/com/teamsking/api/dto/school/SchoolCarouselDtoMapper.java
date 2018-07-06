package com.teamsking.api.dto.school;

import com.teamsking.domain.entity.school.SchoolCarousel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
*@author linhao
*/
@Mapper
public interface SchoolCarouselDtoMapper {

    SchoolCarouselDtoMapper INSTANCE = Mappers.getMapper(SchoolCarouselDtoMapper.class);

    List<SchoolCarouselDto> entityListToDtoList(List<SchoolCarousel> entityList);

    SchoolCarouselDto entityToDto(SchoolCarousel schoolCarousel);

    SchoolCarousel dtoToEntity(SchoolCarouselDto schoolCarouselDto);

}
