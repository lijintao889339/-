package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseCategoryDto extends Dto {

    private Integer id;

    private String label;

    private Integer parentId;

    private Integer displayOrder;

    private Boolean isShow;

    private Boolean isFirstLabel;

}
