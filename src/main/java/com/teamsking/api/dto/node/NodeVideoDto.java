package com.teamsking.api.dto.node;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class NodeVideoDto extends Dto {

    private Integer Id;

    private Integer openId;

    private String title;

    private Integer nodeType;

    private Integer deleteStatus;

    private Integer chapterId;

    private Integer sectionId;

    private Integer seconds;

    private Integer watchProgress;

    private String watchRate;

}
