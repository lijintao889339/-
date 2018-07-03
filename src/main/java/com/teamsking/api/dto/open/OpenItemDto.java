package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;


@Data
public class OpenItemDto extends Dto {

    private Integer id;
    private Integer chapterId;
    private Integer sectionId;
    private Integer openId;
    private Integer courseId;
    private Integer itemType;
    private String itemName;
    private Integer contentId;
    private Integer displayOrder;
    private Integer assetId;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
