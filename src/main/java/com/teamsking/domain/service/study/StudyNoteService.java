package com.teamsking.domain.service.study;

import com.teamsking.domain.entity.study.StudyNote;
import com.teamsking.domain.repository.StudyNoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudyNoteService {


    @Autowired
    StudyNoteMapper studyNoteMapper;


    /**
     * 获取学习-笔记管理列表
     * @return
     */
    public List<StudyNote> list(){

        return studyNoteMapper.selectAll();

    }


    /**
     * 添加学习-笔记管理
     * @param studyNote
     * @return
     */
    public int save(StudyNote studyNote){

        return studyNoteMapper.insert(studyNote);

    }

    /**
     * 删除学习-笔记管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyNoteMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-笔记管理
     * @param studyNote
     * @return
     */
    public int modify(StudyNote studyNote){

        return studyNoteMapper.updateByPrimaryKeySelective(studyNote);

    }

}
