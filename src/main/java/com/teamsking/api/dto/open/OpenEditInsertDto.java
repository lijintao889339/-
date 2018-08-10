package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class OpenEditInsertDto extends Dto {

    private Integer id;

    //private Integer courseId;

    //private Integer categoryId;

    private Integer categoryId;

    private Integer firstCategoryId;

    private String openName;

    private String keyWord;

    private Integer openFree;

    private String openIntroduce;

    private Date beginTime;

    private Date endTime;

    //private Integer schoolId;

    private String openCover;

    private Integer[] teacherId;

    private Integer[] userId;


}
