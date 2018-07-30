package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CourseTeacherDto extends Dto {

    private Integer id;

    private String teacherName;

    private String weChat;

    private String micoBlog;

    private String teacherType;

    private String teacherAvatar;

    private String teacherInfo;

    private String[] tagName;
}
