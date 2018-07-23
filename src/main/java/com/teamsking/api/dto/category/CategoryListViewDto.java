package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import com.teamsking.domain.entity.category.Category;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CategoryListViewDto extends Dto{

    private Integer id;

    private String label;

    private List<AddCategoryNameDto> categories;

}
