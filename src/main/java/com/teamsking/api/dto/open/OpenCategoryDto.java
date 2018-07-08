package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class OpenCategoryDto extends Dto {

    private Integer id;

    private String categoryCode;

    private String categoryName;

    private Integer courseId;

    private Integer openId;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
