package com.teamsking.domain.repository;

import com.teamsking.domain.entity.course.CourseTeacherTag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseTeacherTagMapper extends Mapper<CourseTeacherTag> {
    int insertCourseTeacherTagByTags(@Param("courseTeacherTagList") List<CourseTeacherTag> courseTeacherTagList);
}