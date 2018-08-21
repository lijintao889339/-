package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenQuestionOption;
import com.teamsking.domain.repository.OpenQuestionOptionMapper;
import java.util.List;

import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OpenQuestionOptionService extends BaseService {

    @Autowired
    OpenQuestionOptionMapper openQuestionOptionMapper;

    /**
     * 获取班次-问题调查选项管理
     * @return
     */
    public List<OpenQuestionOption> list(){

        return openQuestionOptionMapper.selectAll();
    }

    /**
     * 添加班次-问题调查选项管理
     * @param openQuestionOption
     * @return
     */
    public int save(OpenQuestionOption openQuestionOption){

        return openQuestionOptionMapper.insert(openQuestionOption);

    }


    /**
     * 删除班次-问题调查选项管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openQuestionOptionMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改班次-问题调查选项管理
     * @param openQuestionOption
     * @return
     */
    public int modify(OpenQuestionOption openQuestionOption){

        return openQuestionOptionMapper.updateByPrimaryKeySelective(openQuestionOption);

    }


    /**
     * 根据问卷ids获取问卷选项信息
     * @param questionIds
     * @return
     */
    public List<OpenQuestionOption> getQuestionOptionInfoByQuestionIds(List<Integer> questionIds) {

        Example questionOptionExample = new Example(OpenQuestionOption.class);
        questionOptionExample.and().andEqualTo("deleteStatus",2);
        questionOptionExample.and().andIn("questionId",questionIds);
        return openQuestionOptionMapper.selectByExample(questionOptionExample);

    }
}
