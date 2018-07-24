package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryListViewDto extends Dto{

    private Integer id;

    private String label;

    private Boolean isFirstLabel;

    private Boolean isShow;

    private List<AddCategoryNameDto> children;

}
