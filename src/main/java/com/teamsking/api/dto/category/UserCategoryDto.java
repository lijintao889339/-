package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class UserCategoryDto extends Dto {

    private Integer id;
    private Integer userId;
    private Integer categoryId;

}
