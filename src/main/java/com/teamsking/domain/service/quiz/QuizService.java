package com.teamsking.domain.service.quiz;

import com.teamsking.api.dto.quiz.QuizDto;
import com.teamsking.api.dto.quiz.QuizDtoMapper;
import com.teamsking.domain.entity.quiz.Quiz;
import com.teamsking.domain.repository.QuizMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 创建考试根据考试id添加智能选题(单选题)
     * @param quizDto
     * @param examId
     * @return
     */
    public int saveOneQuiz(QuizDto quizDto,Integer examId){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quizDto);
        quizEntity.setExamId(examId);
        quizEntity.setDeleteStatus(2);
        quizEntity.setUseType(20);
        quizEntity.setQuizType(10);

        return quizMapper.insert(quizEntity);

    }


    /**
     * 创建考试根据考试id添加智能选题(多选题)
     * @param quizDto
     * @param examId
     * @return
     */
    public int saveMuchQuiz(QuizDto quizDto,Integer examId){

        Quiz quizEntity = QuizDtoMapper.INSTANCE.dtoToEntity(quizDto);
        quizEntity.setExamId(examId);
        quizDto.setDeleteStatus(2);
        quizEntity.setUseType(20);
        quizEntity.setQuizType(20);

        return quizMapper.insert(quizEntity);

    }

}
