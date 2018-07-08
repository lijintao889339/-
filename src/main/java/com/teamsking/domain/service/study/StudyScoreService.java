package com.teamsking.domain.service.study;


import com.teamsking.domain.entity.study.StudyScore;
import com.teamsking.domain.repository.StudyScoreMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyScoreService {

    @Autowired
    StudyScoreMapper studyScoreMapper;

    /**
     * 获取学习-成绩列表
     * @return
     */
    public List<StudyScore> list(){

        return studyScoreMapper.selectAll();

    }


    /**
     * 添加学习-成绩
     * @param studyScore
     * @return
     */
    public int save(StudyScore studyScore){

        return studyScoreMapper.insert(studyScore);

    }


    /**
     * 删除学习-成绩
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyScoreMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-成绩
     * @param studyScore
     * @return
     */
    public int modify(StudyScore studyScore){

        return studyScoreMapper.updateByPrimaryKeySelective(studyScore);

    }

}
