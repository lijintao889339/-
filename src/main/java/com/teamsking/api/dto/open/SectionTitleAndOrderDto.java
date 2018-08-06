package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class SectionTitleAndOrderDto extends Dto {

    private Integer id;

    private String label;

    private Integer diaplayOrder;

    private Boolean isFirstLabel;

}
