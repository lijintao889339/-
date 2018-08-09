package com.teamsking.api.dto.course;

import com.teamsking.api.dto.Dto;
import com.teamsking.api.dto.sys.UserDto;
import lombok.Data;

import java.util.List;

/**
*@author linhao
*/
@Data
public class CourseBeforeEditDto extends Dto {

    private Integer id;

    private String courseName;

    private String courseCover;

    private String keyWord;

    private Integer courseFree;

    private String courseIntroduction;

    //二级分类id
    //private Integer categoryId;

    //二级分类名称
    //private String secondCourseCategoryList;

    //一级分类名称
    //private String firstCourseCategoryList;

    //二级分类Id
    //private Integer firstCategoryId;

    //一级分类
    private CourseCategoryNameDto firstCourseCategoryList;

    //二级分类
    private CourseCategoryNameDto secondCourseCategoryList;

    private List<CourseTeacherNameDto> courseTeacherList;

    private Integer teacherId;

    private Integer userId;

    private List<UserDto> userDtoList;
}
