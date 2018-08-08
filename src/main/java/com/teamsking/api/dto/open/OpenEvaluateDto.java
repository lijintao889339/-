package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;


@Data
public class OpenEvaluateDto extends Dto {

    private Integer id;

    private Integer firstCategoryId;

    private Integer userId;

    private String studentNo;

    private Integer openId;

    private String userName;

    private String openName;

    private Double evaluate;

    private List<String> teacherName;

    private Boolean isShow;

    private String categoryName;

    private String avatar;

    private String evaluateContent;


}
