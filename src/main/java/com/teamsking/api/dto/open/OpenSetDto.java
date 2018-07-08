package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;


@Data
public class OpenSetDto extends Dto {


    private Integer id;
    private Integer schoolId;
    private Integer offlinePercent;
    private Integer onlinePercent;
    private Integer videoPercent;
    private Integer quizPercent;
    private Integer assignmentPercent;
    private Integer topicPercent;
    private Integer examPercent;
    private Integer videoOver;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
