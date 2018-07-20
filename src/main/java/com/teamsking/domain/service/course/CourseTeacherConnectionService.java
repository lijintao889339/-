package com.teamsking.domain.service.course;

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

    public List<CourseTeacherConnection> getTeacherByCourseIdList(List<Integer> courseIds) {

        Example courseTeacherConnectionExample = new Example(CourseTeacherConnection.class);
        Example.Criteria cri = courseTeacherConnectionExample.createCriteria();
        cri.andIn("courseId",courseIds);
        return courseTeacherConnectionMapper.selectByExample(courseTeacherConnectionExample);
    }
}
