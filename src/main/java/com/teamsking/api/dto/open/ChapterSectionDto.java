package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

@Data
public class ChapterSectionDto extends Dto {

    private Integer id;

    private String label;

    private Boolean isFirstLabel;

    private List<SectionTitleAndOrderDto> children;

}
