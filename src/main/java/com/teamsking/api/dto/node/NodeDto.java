package com.teamsking.api.dto.node;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class NodeDto extends Dto {

    private Integer Id;
    private Integer schoolId;
    private Integer nodeType;
    private Integer folderId;
    private Integer courseId;
    private Integer openId;
    private String title;
    private String coverPath;
    private String srtPath;
    private String filePath;
    private Integer seconds;
    private Integer fileSize;
    private Integer pageNum;
    private Integer srtSize;
    private Integer quizNum;
    private Integer displayOrder;
    private String md5Code;
    private String nodeUuid;
    private Byte transProgress;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
