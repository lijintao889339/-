package com.teamsking.api.dto.announce;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class AnnounceDto extends Dto {

    private Integer id;

    private String title;

    private Integer announceType;

    private Integer schoolId;

    private Integer openId;

    private Integer displayOrder;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String link;

    private String content;

}
