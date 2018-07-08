package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CourseWareDto extends Dto {

    private Integer id;
    private Integer courseId;
    private String title;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;
    private Integer assetId;
    private String description;


}
