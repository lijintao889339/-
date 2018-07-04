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
     * @return
     */
    public List<Quiz> list(){

        return quizMapper.selectAll();
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
     * @param id
     * @return
     */
    public int remove(int id){

        return quizMapper.deleteByPrimaryKey(id);
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
