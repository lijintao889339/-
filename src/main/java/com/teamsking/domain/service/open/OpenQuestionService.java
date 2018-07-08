package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenQuestion;
import com.teamsking.domain.repository.OpenQuestionMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenQuestionService {

    @Autowired
    OpenQuestionMapper openQuestionMapper;

    /**
     * 获取班次-问卷调查管理列表
     * @return
     */
    public List<OpenQuestion> list(){

        return openQuestionMapper.selectAll();
    }

    /**
     *添加班次-问卷调查管理
     * @param openQuestion
     * @return
     */
    public int save(OpenQuestion openQuestion){

        return openQuestionMapper.insert(openQuestion);
    }

    /**
     *删除班次-问卷调查管理
     * @param id
     * @return
     */
    public int remove(int id){

        return openQuestionMapper.deleteByPrimaryKey(id);
    }

    /**
     *添加班次-问卷调查管理
     * @param openQuestion
     * @return
     */
    public int modify(OpenQuestion openQuestion){

        return openQuestionMapper.updateByPrimaryKeySelective(openQuestion);
    }

}
