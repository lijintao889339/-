package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OpenGroupDto extends Dto {


    private Integer id;

    private String groupName;

    private Integer userCount;

    private List<String> userNameList;

}
