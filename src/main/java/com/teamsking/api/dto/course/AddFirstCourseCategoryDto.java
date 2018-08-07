package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class AddFirstCourseCategoryDto extends Dto {

    private Integer id;

    private String label;

    private Integer parentId;

    private String imageUrl;

    private Boolean isShow;

    private String keyWord;

    private String description;

    private Boolean isFirstLabel;

}
