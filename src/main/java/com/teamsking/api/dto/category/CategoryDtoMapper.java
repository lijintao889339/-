package com.teamsking.api.dto.category;

import com.teamsking.domain.entity.category.Category;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDtoMapper {


    CategoryDtoMapper INSTANCE = Mappers.getMapper(CategoryDtoMapper.class);

    List<CategoryDto> entityListToDtoList(List<Category> entityList);

    CategoryDto entityToDto(Category category);

    Category dtoToEntity(CategoryDto categoryDto);




}
