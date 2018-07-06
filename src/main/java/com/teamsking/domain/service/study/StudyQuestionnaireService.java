package com.teamsking.domain.service.study;

import com.teamsking.domain.entity.study.StudyQuestionnaire;
import com.teamsking.domain.repository.StudyQuestionnaireMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudyQuestionnaireService {

    @Autowired
    StudyQuestionnaireMapper studyQuestionnaireMapper;


    /**
     * 获取学习-问卷调查记录管理
     * @return
     */
    public List<StudyQuestionnaire> list(){

        return studyQuestionnaireMapper.selectAll();

    }


    /**
     * 添加学习-问卷调查记录管理
     * @param studyQuestionnaire
     * @return
     */
    public int save(StudyQuestionnaire studyQuestionnaire){

        return studyQuestionnaireMapper.insert(studyQuestionnaire);



    }


    /**
     * 删除学习-问卷调查记录管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyQuestionnaireMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-问卷调查记录管理
     * @param studyQuestionnaire
     * @return
     */
    public int modify(StudyQuestionnaire studyQuestionnaire){

        return studyQuestionnaireMapper.updateByPrimaryKeySelective(studyQuestionnaire);

    }

}
