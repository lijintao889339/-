package com.teamsking.api.dto.node;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class NodeDocDto extends Dto {

    private Integer Id;

    private Integer openId;

    private String title;

    private Integer nodeType;

    private Integer deleteStatus;

    private Integer chapterId;

    private Integer sectionId;

    private String content;

}
