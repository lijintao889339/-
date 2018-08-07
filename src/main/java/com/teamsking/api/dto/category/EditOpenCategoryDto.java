package com.teamsking.api.dto.category;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class EditOpenCategoryDto extends Dto {

    private Integer id;
    private String label;
    private Boolean isShow;
    private Integer updateId;
    private Date updatedTime;

}
