package com.teamsking.domain.service.course;

import com.google.common.collect.Lists;
import com.teamsking.domain.entity.course.CourseTeacherConnection;
import com.teamsking.domain.repository.CourseTeacherConnectionMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseTeacherConnectionService extends BaseService {

    @Autowired
    CourseTeacherConnectionMapper courseTeacherConnectionMapper;

    /**
     * 根据课程Ids获取老师课程关系表信息
     * @param courseIds
     * @return
     */
    public List<CourseTeacherConnection> getTeacherByCourseIdList(List<Integer> courseIds) {

        Example courseTeacherConnectionExample = new Example(CourseTeacherConnection.class);
        Example.Criteria cri = courseTeacherConnectionExample.createCriteria();
        cri.andIn("courseId",courseIds);
        return courseTeacherConnectionMapper.selectByExample(courseTeacherConnectionExample);
    }

    /**
     * 根据课程Id获取老师课程关系表信息
     * @param courseId
     * @return
     */
    public List<CourseTeacherConnection> getTeacherByCourseId(Integer courseId) {

        CourseTeacherConnection courseTeacherConnection = new CourseTeacherConnection();
        courseTeacherConnection.setCourseId(courseId);
        return courseTeacherConnectionMapper.select(courseTeacherConnection);
    }


    /**
     * 根据课程id删除与其相关的老师记录
     * @param courseId
     * @return
     */
    public int removeConnectionByCourseId(Integer courseId) {

        Example example = new Example(CourseTeacherConnection.class);
        example.and().andEqualTo("courseId",courseId);
        return courseTeacherConnectionMapper.deleteByExample(example);
    }
}
