package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenQuestionSubject;
import com.teamsking.domain.repository.OpenQuestionSubjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenQuestionSubjectService {

    @Autowired
    OpenQuestionSubjectMapper openQuestionSubjectMapper;

    /**
     * 获取班次-问卷调查题干管理列表
     * @return
     */
    public List<OpenQuestionSubject> list(){

        return openQuestionSubjectMapper.selectAll();
    }

    /**
     * 添加班次-问卷调查题干管理
     * @param openQuestionSubject
     * @return
     */
    public int save(OpenQuestionSubject openQuestionSubject){

        return openQuestionSubjectMapper.insert(openQuestionSubject);
    }

    /**
     * 删除班次-问卷调查题干管理
     * @param id
     * @return
     */
    public int remove(int id){

        return openQuestionSubjectMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改班次-问卷调查题干管理
     * @param openQuestionSubject
     * @return
     */
    public int modify(OpenQuestionSubject openQuestionSubject){

        return openQuestionSubjectMapper.updateByPrimaryKeySelective(openQuestionSubject);
    }

}
