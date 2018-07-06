package com.teamsking.api.dto.tag;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class TagDto extends Dto {

    private Integer id;
    private String tagName;
    private Integer schoolId;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;
    private Integer deleteStatus;

}
