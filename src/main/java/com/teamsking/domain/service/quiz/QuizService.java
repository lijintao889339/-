package com.teamsking.domain.service.quiz;

import com.teamsking.domain.entity.quiz.Quiz;
import com.teamsking.domain.repository.QuizMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class QuizService {

    @Autowired
    QuizMapper quizMapper;

    /**
     * 获取试题列表
     * @param quiz
     * @return
     */
    public List<Quiz> list(Quiz quiz){

        return quizMapper.select(quiz);
    }

    /**
     * 添加试题
     * @param quiz
     * @return
     */
    public int save(Quiz quiz){

        return quizMapper.insert(quiz);
    }

    /**
     * 删除试题
     * @param quiz
     * @return
     */
    public int remove(Quiz quiz){

        return quizMapper.delete(quiz);
    }

    /**
     * 修改试题
     * @param quiz
     * @return
     */
    public int modify(Quiz quiz){

        return quizMapper.updateByPrimaryKeySelective(quiz);
    }

}
