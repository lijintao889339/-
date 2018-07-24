package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class AddCourseCategoryDto extends Dto {

    private Integer id;
    private String label;
    private Integer parentId;
    private String imageUrl;
    private String keyWord;
    private Boolean isShow;
    private String description;
    private Boolean isFirstLabel;

}
