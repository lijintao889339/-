package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class AddCourseItemDto extends Dto {

    private Integer nodeType;

    private String suffixName;

    private String title;

    private String filePath;
}
