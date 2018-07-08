package com.teamsking.api.dto.school;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class SchoolDto extends Dto {

    private Integer id;

    private String schoolName;

    private String shortName;

    private String enName;

    private String schoolLogo;

    private String advertisePic;

    private String schoolWeburl;

    private String introduction;

    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String schoolAddress;

    private Integer displayOrder;

    private Integer template;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

}
