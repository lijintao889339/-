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

    //班课老师名称
    private String teacherName;

    //老师头像
    private String teacherAvatar;

    //老师职位（头衔）
    private String teacherType;

    //老师介绍
    private String teacherInfo;

    //微信
    private String weChat;

    //微博
    private String micoBlog;

    //标签
    private String[] tagName;


}
