package com.teamsking.api.dto.sys;

import com.teamsking.api.dto.Dto;
import lombok.Data;

/**
*@author linhao
*/
@Data
public class SysConfigDto extends Dto {

    private Integer id;

    private String configCode;

    private Integer configValue;

    private String decription;

}
