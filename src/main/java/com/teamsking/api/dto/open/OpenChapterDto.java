package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenChapterDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer courseId;

    private String chapterName;

    private Date beginDatetime;

    private Date endDatetime;

    private Integer displayOrder;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private Integer chapterStatus;

}
