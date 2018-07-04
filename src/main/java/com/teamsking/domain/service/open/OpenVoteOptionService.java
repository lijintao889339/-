package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenVoteOption;
import com.teamsking.domain.repository.OpenVoteOptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenVoteOptionService {

    @Autowired
    OpenVoteOptionMapper openVoteOptionMapper;

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
}
