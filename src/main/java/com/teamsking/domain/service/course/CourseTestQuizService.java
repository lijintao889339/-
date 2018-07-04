package com.teamsking.domain.service.course;

import com.teamsking.domain.entity.course.CourseTestQuiz;
import com.teamsking.domain.repository.CourseTestQuizMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class CourseTestQuizService {

    @Autowired
    CourseTestQuizMapper courseTestQuizMapper;

    /**
     *获取课程-测验与试题的关系的列表
     * @return
     */
    public List<CourseTestQuiz> list(){

        return courseTestQuizMapper.selectAll();
    }

    /**
     *添加课程-测验与试题关系
     * @param courseTestQuiz
     * @return
     */
    public int save(CourseTestQuiz courseTestQuiz){

        return courseTestQuizMapper.insert(courseTestQuiz);
    }

    /**
     *删除课程-测验与试题关系
     * @param id
     * @return
     */
    public int remove(int id){

        return courseTestQuizMapper.deleteByPrimaryKey(id);
    }

    /**
     *课程修改-测验与试题关系
     * @param courseTestQuiz
     * @return
     */
    public int modify(CourseTestQuiz courseTestQuiz){

        return courseTestQuizMapper.updateByPrimaryKeySelective(courseTestQuiz);
    }
}
