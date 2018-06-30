package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.repository.CourseTeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    /**
     * 获取课程-教师列表
     * @param courseTeacher
     * @return
     */
    public List<CourseTeacher> list(CourseTeacher courseTeacher){
        return courseTeacherMapper.select(courseTeacher);
    }

    /**
     * 添加课程-教师
     * @param courseTeacher
     * @return
     */
    public int save(CourseTeacher courseTeacher){
        return courseTeacherMapper.insert(courseTeacher);
    }

    /**
     * 删除课程-教师
     * @param courseTeacher
     * @return
     */
    public int remove(CourseTeacher courseTeacher){
        return courseTeacherMapper.delete(courseTeacher);

    }

    /**
     * 修改课程-教师
     * @param courseTeacher
     * @return
     */
    public int modify(CourseTeacher courseTeacher){
        return courseTeacherMapper.updateByPrimaryKeySelective(courseTeacher);
    }


}
