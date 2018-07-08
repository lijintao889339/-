package com.teamsking.api.dto.node;


import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class NodeFolderDto extends Dto {

    private Integer id;
    private String folderName;
    private Integer parentId;
    private Integer courseId;
    private Integer openId;
    private Integer folderType;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;


}
