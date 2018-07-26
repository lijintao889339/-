package com.teamsking.api.dto.category;

import com.teamsking.api.dto.open.AddOpenDto;
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

    Category dtoToEntity1(AddCourseCategoryDto addCourseCategoryDto);

    Category dtoToEntity2(AddCategoryNameDto addCategoryNameDto);

    Category dtoToEntity3(EditCourseCategoryDto editCourseCategoryDto);

    List<AddCourseCategoryDto> entityListToDtoList1(List<Category> entityList);

    List<CategoryListViewDto> entityToListViewDtoList(List<Category> categories);

    Category insertDtoAddToEntity(AddOpenDto addOpenDto);


}
