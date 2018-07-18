package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class AddCategoryNameDto extends Dto {

    private Integer id;
    private String categoryName;
    private Integer parentId;

}
