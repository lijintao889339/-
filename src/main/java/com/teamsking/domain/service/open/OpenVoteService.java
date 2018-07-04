package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenVote;
import com.teamsking.domain.repository.OpenVoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OpenVoteService {

    @Autowired
    OpenVoteMapper openVoteMapper;

    /**
     * 获取班次-投票管理列表
     * @return
     */
    public List<OpenVote> list(){
        return openVoteMapper.selectAll();
    }

    /**
     * 添加班次-投票管理
     * @param openVote
     * @return
     */
    public int save(OpenVote openVote){

        return openVoteMapper.insert(openVote);

    }

    /**
     * 删除班次-投票管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openVoteMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-投票管理
     * @param openVote
     * @return
     */
    public int modify(OpenVote openVote){

        return openVoteMapper.updateByPrimaryKeySelective(openVote);

    }

}
