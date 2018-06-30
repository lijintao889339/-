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
     * @param courseTestQuiz
     * @return
     */
    public List<CourseTestQuiz> list(CourseTestQuiz courseTestQuiz){

        return courseTestQuizMapper.select(courseTestQuiz);
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
     * @param courseTestQuiz
     * @return
     */
    public int remove(CourseTestQuiz courseTestQuiz){

        return courseTestQuizMapper.delete(courseTestQuiz);
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
