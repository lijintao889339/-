package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class SectionTitleAndOrderDto extends Dto {

    private Integer id;

    private String title;

    private Integer diaplayOrder;

}
