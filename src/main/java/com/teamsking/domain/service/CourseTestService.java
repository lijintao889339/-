package com.teamsking.domain.service;

import com.teamsking.domain.entity.CourseTest;
import com.teamsking.domain.repository.CourseTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseTestService {

    @Autowired
    CourseTestMapper courseTestMapper;

    /**
     * 获取课程-测验列表
     * @param courseTest
     * @return
     */
    public List<CourseTest> list(CourseTest courseTest){

        return courseTestMapper.select(courseTest);
    }

    /**
     *添加课程-测验
     * @param courseTest
     * @return
     */
    public int save(CourseTest courseTest){

        return courseTestMapper.insert(courseTest);
    }

    /**
     *删除课程-测验
     * @param courseTest
     * @return
     */
    public int remove(CourseTest courseTest){

        return courseTestMapper.delete(courseTest);
    }

    /**
     *修改课程-测验
     * @param courseTest
     * @return
     */
    public int modify(CourseTest courseTest){

        return courseTestMapper.updateByPrimaryKeySelective(courseTest);
    }

}
