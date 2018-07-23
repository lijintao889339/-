package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenCopyDto extends Dto {


    private Integer id;
    private Date beginTime;
    private Date endTime;
    private String courseName;
    private Integer copy;
    private String schoolName;
    private Integer openMode;



}
