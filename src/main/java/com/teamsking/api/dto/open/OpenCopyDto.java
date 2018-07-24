package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenCopyDto extends Dto {
    private Integer openId;
    private Date beginTime;
    private Date endTime;
    private String openName;
    private String openCover;
    private Integer copyInfo;
    private String schoolName;
    private Integer openMode;



}
