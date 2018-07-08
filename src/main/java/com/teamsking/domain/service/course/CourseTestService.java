package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseTest;
import com.teamsking.domain.repository.CourseTestMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return
     */
    public List<CourseTest> list(){

        return courseTestMapper.selectAll();
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
     * @param id
     * @return
     */
    public int remove(int id){

        return courseTestMapper.deleteByPrimaryKey(id);
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
