package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseTopic;
import com.teamsking.domain.repository.CourseTopicMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseTopicService {

    @Autowired
    CourseTopicMapper courseTopicMapper;

    /**
     * 获取课程讨论管理列表
     * @return
     */
    public List<CourseTopic> list(){

        return courseTopicMapper.selectAll();
    }

    /**
     *添加课程讨论
     * @param courseTopic
     * @return
     */
    public int save(CourseTopic courseTopic){

        return courseTopicMapper.insert(courseTopic);
    }

    /**
     *删除课程讨论
     * @param id
     * @return
     */
    public int remove(int id){

        return courseTopicMapper.deleteByPrimaryKey(id);
    }

    /**
     *修改课程讨论
     * @param courseTopic
     * @return
     */
    public int modify(CourseTopic courseTopic){

        return courseTopicMapper.updateByPrimaryKeySelective(courseTopic);
    }

}
