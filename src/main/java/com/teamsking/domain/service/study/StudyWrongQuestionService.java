package com.teamsking.domain.service.study;


import com.teamsking.domain.entity.study.StudyWrongQuestion;
import com.teamsking.domain.repository.StudyWrongQuestionMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyWrongQuestionService {


    @Autowired
    StudyWrongQuestionMapper studyWrongQuestionMapper;


    /**
     * 获取学习-错题记录管理列表
     * @return
     */
    public List<StudyWrongQuestion> list(){

        return studyWrongQuestionMapper.selectAll();

    }

    /**
     * 添加学习-错题记录管理
     * @param studyWrongQuestion
     * @return
     */
    public int save(StudyWrongQuestion studyWrongQuestion){

        return studyWrongQuestionMapper.insert(studyWrongQuestion);

    }

    /**
     * 删除学习-错题记录管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyWrongQuestionMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-错题记录管理
     * @param studyWrongQuestion
     * @return
     */
    public int modify(StudyWrongQuestion studyWrongQuestion){

        return studyWrongQuestionMapper.updateByPrimaryKeySelective(studyWrongQuestion);

    }

}
