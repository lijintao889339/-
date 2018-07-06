package com.teamsking.domain.service.study;

import com.teamsking.domain.entity.study.StudyVote;
import com.teamsking.domain.repository.StudyVoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudyVoteService {

    @Autowired
    StudyVoteMapper studyVoteMapper;

    /**
     * 获取学习-投票列表
     * @return
     */
    public List<StudyVote> list(){

        return studyVoteMapper.selectAll();

    }

    /**
     * 添加学习-投票
     * @param studyVote
     * @return
     */
    public int save(StudyVote studyVote){

        return studyVoteMapper.insert(studyVote);

    }


    /**
     * 删除学习-投票
     * @param id
     * @return
     */
    public int remove(Integer id){

        return studyVoteMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改学习-投票
     * @param studyVote
     * @return
     */
    public int modify(StudyVote studyVote){

        return studyVoteMapper.updateByPrimaryKeySelective(studyVote);

    }

}
