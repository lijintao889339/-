package com.teamsking.api.dto.node;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class NodeForderNameDto extends Dto {

    private Integer id;

    private Integer parentId;

    private String folderName;

}
