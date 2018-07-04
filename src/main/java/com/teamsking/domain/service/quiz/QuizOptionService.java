package com.teamsking.domain.service.quiz;

import com.teamsking.domain.entity.quiz.QuizOption;
import com.teamsking.domain.repository.QuizOptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class QuizOptionService {

    @Autowired
    QuizOptionMapper quizOptionMapper;

    /**
     * 试题选项列表
     * @return
     */
    public List<QuizOption> list(){

        return quizOptionMapper.selectAll();
    }

    /**
     * 添加试题选项
     * @param quizOption
     * @return
     */
    public int save(QuizOption quizOption){

        return quizOptionMapper.insert(quizOption);
    }

    /**
     * 删除试题选项
     * @param id
     * @return
     */
    public int remove(int id){

        return quizOptionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改试题选项
     * @param quizOption
     * @return
     */
    public int modify(QuizOption quizOption){

        return quizOptionMapper.updateByPrimaryKeySelective(quizOption);
    }

}
