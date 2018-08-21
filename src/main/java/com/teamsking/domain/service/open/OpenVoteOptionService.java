package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenVoteOption;
import com.teamsking.domain.repository.OpenVoteOptionMapper;
import java.util.List;

import com.teamsking.domain.repository.StudyVoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenVoteOptionService {

    @Autowired
    OpenVoteOptionMapper openVoteOptionMapper;
    @Autowired
    StudyVoteMapper studyVoteMapper;

    /**
     * 获取班次-投票选项管理列表
     * @return
     */
    public List<OpenVoteOption> list(){

        return openVoteOptionMapper.selectAll();
    }

    /**
     * 添加班次-投票选项管理
     * @param openVoteOption
     * @return
     */
    public int save(OpenVoteOption openVoteOption){

        return openVoteOptionMapper.insert(openVoteOption);
    }

    /**
     * 删除班次-投票选项管理
     * @param id
     * @return
     */
    public int remove(int id){

        return openVoteOptionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改班次-投票选项管理
     * @param openVoteOption
     * @return
     */
    public int modify(OpenVoteOption openVoteOption){

        return openVoteOptionMapper.updateByPrimaryKeySelective(openVoteOption);
    }

    /**
     * 根据投票Ids获取投票选项信息列表
     * @param voteIds
     * @return
     */
    public List<OpenVoteOption> getVoteOptionInfoByVoteIds(List<Integer> voteIds) {

        Example voteOptionExample = new Example(OpenVoteOption.class);
        voteOptionExample.and().andEqualTo("deleteStatus",2);
        voteOptionExample.and().andIn("voteId",voteIds);
        return openVoteOptionMapper.selectByExample(voteOptionExample);
    }
}
