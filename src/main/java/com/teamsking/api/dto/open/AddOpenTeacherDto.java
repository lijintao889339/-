package com.teamsking.api.dto.open;

import com.teamsking.api.dto.Dto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class AddOpenTeacherDto extends Dto {

    private Integer id;

    private String teacherName;

    private String teacherAvatar;

    private String teacherType;

    private String teacherInfo;

    private String weChat;

    private String micoBlog;

    private String[] tagName;


}
