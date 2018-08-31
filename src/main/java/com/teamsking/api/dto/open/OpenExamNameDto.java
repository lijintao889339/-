package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OpenExamNameDto extends Dto {

    private Integer id;

    private String title;

    private Integer openId;

    private Date startTime;

    private Date endTime;

    private Integer examType;

    private String state;

    private List<String> teacherNames;

    private String status;

    private Integer userCount;

    //private Integer notUserCount;

    private Integer stopUserCount;

    private Integer submitCount;

}
