package com.teamsking.api.dto.school;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class SchoolNewsDto extends Dto {

    private Integer id;

    private Integer schoolId;

    private String newsTitle;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private String newsContent;

}
