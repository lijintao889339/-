package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class ChapterSectionDto extends Dto {

    private Integer id;

    private String chapterName;

    private List<SectionTitleAndOrderDto> sectionDtoList;

}
