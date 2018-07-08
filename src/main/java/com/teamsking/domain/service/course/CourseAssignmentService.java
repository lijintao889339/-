package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseAssignment;
import com.teamsking.domain.repository.CourseAssignmentMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseAssignmentService {

    @Autowired
    CourseAssignmentMapper courseAssignmentMapper;

    /**
     * 获取课程-作业列表
     *
     * @return
     */
    public List<CourseAssignment> list(){

        return courseAssignmentMapper.selectAll();
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
     * @param id
     * @return
     */
    public int remove(Integer id){
        return courseAssignmentMapper.deleteByPrimaryKey(id);

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
