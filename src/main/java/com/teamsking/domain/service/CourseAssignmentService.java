package com.teamsking.domain.service;

import com.teamsking.domain.entity.CourseAssignment;
import com.teamsking.domain.repository.CourseAssignmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseAssignmentService {

    @Autowired
    CourseAssignmentMapper courseAssignmentMapper;

    /**
     * 获取课程-作业列表
     * @param courseAssignment
     * @return
     */
    public List<CourseAssignment> list(CourseAssignment courseAssignment){

        return courseAssignmentMapper.select(courseAssignment);
    }

    /**
     * 添加课程-作业
     * @param courseAssignment
     * @return
     */
    public int save(CourseAssignment courseAssignment){
        return courseAssignmentMapper.insert(courseAssignment);
    }

    /**
     * 删除课程-作业
     * @param courseAssignment
     * @return
     */
    public int remove(CourseAssignment courseAssignment){
        return courseAssignmentMapper.delete(courseAssignment);

    }

    /**
     * 修改课程-作业
     * @param courseAssignment
     * @return
     */
    public int modify(CourseAssignment courseAssignment){
        return courseAssignmentMapper.updateByPrimaryKeySelective(courseAssignment);
    }

}
