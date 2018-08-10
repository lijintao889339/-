package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseItemDto extends Dto {

    private Integer id;

    private String Name;

    private Integer itemType;

    private Integer type;

    private Integer relationId;
}
