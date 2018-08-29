package com.teamsking.domain.service.open;


import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenActivityDto;
import com.teamsking.api.dto.open.OpenActivityDtoMapper;
import com.teamsking.api.dto.open.OpenVoteDtoMapper;
import com.teamsking.api.dto.open.OpenVoteQueryDto;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.open.OpenActivity;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.open.OpenVote;
import com.teamsking.domain.entity.study.StudyVote;
import com.teamsking.domain.repository.OpenActivityMapper;

import java.util.Date;
import java.util.List;

import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.OpenVoteMapper;
import com.teamsking.domain.repository.StudyVoteMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenActivityService extends BaseService {


    @Autowired
    OpenActivityMapper openActivityMapper;
    @Autowired
    OpenVoteMapper openVoteMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    StudyVoteMapper studyVoteMapper;

    /**
     * 获取班次-活动列表
     *
     * @return
     */
    public List<OpenActivity> list(){
        return openActivityMapper.selectAll();
    }

    /**
     * 添加班次-活动
     * @param openActivity
     * @return
     */
    public int save(OpenActivity openActivity){

        return openActivityMapper.insert(openActivity);

    }

    /**
     * 删除班次-活动
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openActivityMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-活动
     * @param openActivity
     * @return
     */
    public int modify(OpenActivity openActivity){

        return openActivityMapper.updateByPrimaryKeySelective(openActivity);

    }

}
