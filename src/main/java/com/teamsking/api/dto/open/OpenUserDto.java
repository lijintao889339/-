package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class OpenUserDto extends Dto {

    private Integer id;

    private Integer openId;

    private Integer courseId;

    private Integer userId;

    private Integer groupId;

    private String groupName;

    private Byte leaderStatus;

    private Integer lastItem;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
