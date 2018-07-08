package com.teamsking.api.dto.school;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class SchoolManagerDto extends Dto {

    private Integer id;

    private String schoolId;

    private Integer userId;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
