package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class EditCourseCategoryDto extends Dto {

    private Integer id;
    private String categoryName;
    private Integer isShow;
    private Integer updateId;
    private Date updatedTime;

}
