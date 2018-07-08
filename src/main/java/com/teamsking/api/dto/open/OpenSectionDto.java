package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenSectionDto extends Dto {

    private Integer id;

    private Integer chapterId;

    private Integer openId;

    private Integer courseId;

    private String title;

    private Integer diaplayOrder;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
