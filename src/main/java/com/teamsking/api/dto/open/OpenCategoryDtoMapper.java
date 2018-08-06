package com.teamsking.api.dto.open;


import com.teamsking.domain.entity.open.OpenCategory;
import java.util.List;

import com.teamsking.domain.entity.open.OpenChapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenCategoryDtoMapper {

    OpenCategoryDtoMapper INSTANCE = Mappers.getMapper(OpenCategoryDtoMapper.class);

    List<OpenCategoryDto> entityListToDtoList(List<OpenCategory> entityList);

    OpenCategoryDto entityToDto(OpenCategory openCategory);

    OpenCategory dtoToEntity(OpenCategoryDto openCategoryDto);

}
