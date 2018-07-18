package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import java.util.Date;
import lombok.Data;

@Data
public class CourseDto extends Dto {

    private Integer id;

    private String courseName;

    private String courseUuid;

    private String simpleInfo;

    private String courseCover;

    private String courseVideo;

    private String courseVideoCover;

    private String courseVideoSrt;

    private String level;

    private Integer schoolId;

    private String courseStatus;

    private Integer createId;

    private Date createTime;

    private Integer updateId;

    private Date updateTime;

    private Integer deleteStatus;

    private Integer  courseFree;

}
