package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class CourseItemDto extends Dto {

    private Integer id;

    private Integer chapterId;

    private Integer sectionId;

    private Integer courseId;

    private Integer itemType;

    private String itemName;

    private Integer displayOrder;

    private Integer relationId;

    private Integer webType;

    private String webName;

    private String webContent;

    private Integer assetId;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
