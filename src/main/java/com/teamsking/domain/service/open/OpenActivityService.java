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

    /**
     * 获取正在进行的活动
     * @param openId
     * @return
     */
    /*public List<OpenActivityDto> listByStarting(int openId) {

        //获取该课程的学生数
        OpenUser openUser = new OpenUser();
        openUser.setDeleteStatus(2);
        openUser.setOpenId(openId);
        int userNums = openUserMapper.selectCount(openUser);


        //获取该班课下的投票
        OpenVote openVote = new OpenVote();
        openVote.setDeleteStatus(2);
        openVote.setPublish(true);
        List<OpenVote> openVoteList = openVoteMapper.select(openVote);

        //数据转换
        //List<OpenVoteQueryDto> openVoteQueryDtoList = OpenVoteDtoMapper.INSTANCE.entityListToQueryDotList();


        //现获取当前时间戳
        Long nowTime = System.currentTimeMillis();


        //获取该班课下的所有活动
        OpenActivity openActivity = new OpenActivity();
        openActivity.setDeleteStatus(2);
        openActivity.setOpenId(openId);
        List<OpenActivity> openActivityLsit = openActivityMapper.select(openActivity);

        //获取活动ids
        *//*List<Integer> activityIds = Lists.newArrayList();
        for (OpenActivity activity : openActivityLsit){
            activityIds.add(activity.getId());
        }*//*

        //数据转换
        List<OpenActivityDto> openActivityDtos = OpenActivityDtoMapper.INSTANCE.entityListToDtoList(openActivityLsit);

        for (OpenActivityDto openActivityDto : openActivityDtos){

            List<OpenVoteQueryDto> openVoteQueryDtoList = Lists.newArrayList();
            for (OpenVote vote : openVoteList){
                if (openActivityDto.getId().intValue() == vote.getActivityId().intValue()){

                    //遍历集合
                    //for (OpenVote vote : openVoteList){
                        //获取已投票人数
                        StudyVote studyVote = new StudyVote();
                        studyVote.setDeleteStatus(2);
                        studyVote.setOptionId(openId);
                        studyVote.setVoteId(vote.getId());
                        int commitUserNums = studyVoteMapper.selectCount(studyVote);

                        //结束时间不为空
                        if (null != vote.getEndTime()){
                            Long endTime = vote.getEndTime().getTime();
                            //当前时间戳小于结束时间戳
                            if (nowTime < endTime){
                                OpenVoteQueryDto openVoteQueryDto = new OpenVoteQueryDto();
                                openVoteQueryDto.setActivityId(vote.getActivityId());
                                openVoteQueryDto.setId(vote.getId());
                                openVoteQueryDto.setActivityType(1);
                                openVoteQueryDto.setStartTime(vote.getStartTime());
                                openVoteQueryDto.setTitle(vote.getTitle());
                                openVoteQueryDto.setAllUserNums(userNums);
                                openVoteQueryDto.setCommitUserNums(commitUserNums);
                                openVoteQueryDtoList.add(openVoteQueryDto);
                            }
                        }
                    //}

                }
            }
            openActivityDto.setOpenVoteDtoQuerys(openVoteQueryDtoList);
        }

        return openActivityDtos;

    }*/
}
