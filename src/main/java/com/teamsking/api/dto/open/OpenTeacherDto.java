package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class OpenTeacherDto extends Dto {


    private Integer id;
    private String teacherName;
    private Integer openId;
    private Integer courseId;
    private Integer userId;
    private Integer displayOrder;
    private String teacherType;
    private String teacherAvatar;
    private String teacherInfo;
    private String teacherOrganization;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;




}
