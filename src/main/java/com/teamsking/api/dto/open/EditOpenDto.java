package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class EditOpenDto extends Dto {

    private Integer id;

    private Integer courseId;

    private Integer categoryId;

    private String openName;

    private Date beginTime;

    private Date endTime;

    private Integer schoolId;

    private String openCover;

    private String courseCover;

    private Integer visibleRange;

    private Integer studyType;

}
