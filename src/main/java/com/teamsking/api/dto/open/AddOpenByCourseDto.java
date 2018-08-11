package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class AddOpenByCourseDto extends Dto {

    private String openName;

    private String keyWord;

    private Integer openFree;

    private String openIntroduce;

    private List<OpenTeacherNameDto> teacherNameDtoList;

    private Integer courseStatus;

}
