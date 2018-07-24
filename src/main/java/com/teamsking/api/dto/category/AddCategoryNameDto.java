package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class AddCategoryNameDto extends Dto {

    private Integer id;

    private String label;

    private Boolean isShow;

    private Boolean isFirstLabel;

    private Integer parentId;

}
