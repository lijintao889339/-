package com.teamsking.api.dto.tag;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class UserTagDto extends Dto {

    private Integer id;
    private Integer userId;
    private Integer tagId;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
