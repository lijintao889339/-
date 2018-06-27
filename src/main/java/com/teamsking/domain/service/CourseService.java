package com.teamsking.domain.service;


import com.teamsking.domain.entity.Course;
import com.teamsking.domain.repository.CourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    /**
     * 获取课程列表
     * @return
     */
    public List<Course> list(){
        return courseMapper.selectAll();

    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    public int save(Course course){
        return courseMapper.insert(course);
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    public int remove(Integer id){
        return courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改课程
     * @param course
     * @return
     */
    public int modify(Course course){
        return courseMapper.updateByPrimaryKeySelective(course);
    }

}
