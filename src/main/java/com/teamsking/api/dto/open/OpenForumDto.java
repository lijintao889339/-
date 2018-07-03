package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;


@Data
public class OpenForumDto extends Dto {

    private Integer id;
    private Integer openId;
    private Integer courseId;
    private Integer schoolId;
    private String title;
    private Integer topicCount;
    private Integer displayOrder;
    private String description;

}
