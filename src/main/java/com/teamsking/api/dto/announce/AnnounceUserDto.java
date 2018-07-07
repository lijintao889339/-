package com.teamsking.api.dto.announce;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

/**
*@author linhao
*/
@Data
public class AnnounceUserDto extends Dto {

    private Integer id;

    private Integer announceId;

    private Integer userId;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
