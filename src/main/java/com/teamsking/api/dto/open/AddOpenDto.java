package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

@Data
public class AddOpenDto extends Dto {

    private Integer id;

    private Integer courseId;

    private Integer categoryId;

    private String openName;

    private Integer[] teacherId;

    private String openCover;

    private Integer visibleRange;

    private String openIntroduce;

    private Integer keyWord;

    private String openFree;
    

}
