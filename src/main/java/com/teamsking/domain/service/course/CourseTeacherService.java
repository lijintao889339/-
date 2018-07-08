package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseTeacher;
import com.teamsking.domain.repository.CourseTeacherMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    /**
     * 获取课程-教师列表
     *
     * @return
     */
    public List<CourseTeacher> list(){
        return courseTeacherMapper.selectAll();
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
     * @param id
     * @return
     */
    public int remove(Integer id){
        return courseTeacherMapper.deleteByPrimaryKey(id);

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
