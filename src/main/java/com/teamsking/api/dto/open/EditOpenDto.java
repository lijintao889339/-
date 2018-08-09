package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import com.teamsking.api.dto.sys.RoleDto;
import com.teamsking.api.dto.sys.UserDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EditOpenDto extends Dto {

    private Integer id;

    private Integer courseId;

    private Integer categoryId;

    private String openName;

    private String keyWord;

    private Integer openFree;

    private String openIntroduce;

    private Date beginTime;

    private Date endTime;

    //private Integer schoolId;

    private String openCover;

    //private String courseCover;

    private Integer visibleRange;

    private Integer studyType;

    private List<OpenTeacherNameDto> openTeacherNameList;

    //private List<UserDto> userDtoListAll;

    //private Integer userId;

    private List<UserDto> userDtoListById;
}
