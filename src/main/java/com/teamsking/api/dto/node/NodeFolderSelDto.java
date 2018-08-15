package com.teamsking.api.dto.node;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class NodeFolderSelDto extends Dto {

    private Integer id;
    private String folderName;
    private Integer parentId;
    private Integer openId;
    private Integer folderType;



}
