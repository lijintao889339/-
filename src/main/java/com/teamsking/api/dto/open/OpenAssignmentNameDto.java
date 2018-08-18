package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import com.teamsking.api.dto.node.NodeForderNameDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OpenAssignmentNameDto extends Dto {

    private Integer id;

    private String folderName;

    private String title;

    private String score;

    private String status;

    private Integer userCount;

    private Integer notUserCount;

    private Integer stopUserCount;

    private Date endTime;

    private List<NodeForderNameDto> children;



}
