package com.teamsking.domain.service.study;

import com.teamsking.domain.entity.study.StudyVisit;
import com.teamsking.domain.repository.StudyVisitMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudyVisitService {

    @Autowired
    StudyVisitMapper studyVisitMapper;

    /**
     * 获取学习-进度列表
     * @return
     */
    public List<StudyVisit> list(){

        return studyVisitMapper.selectAll();

    }

    /**
     * 添加学习-进度
     * @param studyVisit
     * @return
     */
    public int save(StudyVisit studyVisit){

        return studyVisitMapper.insert(studyVisit);

    }


    /**
     * 删除学习-进度
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyVisitMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-进度
     * @param studyVisit
     * @return
     */
    public int modify(StudyVisit studyVisit){

        return studyVisitMapper.updateByPrimaryKeySelective(studyVisit);

    }


}
