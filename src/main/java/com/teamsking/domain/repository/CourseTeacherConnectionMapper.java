package com.teamsking.domain.repository;

import com.teamsking.domain.entity.course.CourseTeacherConnection;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseTeacherConnectionMapper extends Mapper<CourseTeacherConnection> {
    int insertConnectionByCourseAndTeachers(@Param("courseTeacherConnectionList") List<CourseTeacherConnection> courseTeacherConnectionList);
}