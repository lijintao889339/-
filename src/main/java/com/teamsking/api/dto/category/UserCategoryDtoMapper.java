package com.teamsking.api.dto.category;


import com.teamsking.domain.entity.category.UserCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserCategoryDtoMapper {

    UserCategoryDtoMapper INSTANCE = Mappers.getMapper(UserCategoryDtoMapper.class);

    List<UserCategoryDto> entityListToDtoList(List<UserCategory> entityList);

    UserCategoryDto entityToDto(UserCategory userCategory);

    UserCategory dtoToEntity(UserCategoryDto userCategoryDto);

}
